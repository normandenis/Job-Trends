package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil.inflate
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.app.ActionBar.LayoutParams
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.view.LayoutInflater.from
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.R.layout.*
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.JOB_STATE
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.AutoCompleteAdapter
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActionbarHomeBinding
import io.jobtrends.jobtrends.databinding.ActivityHomeBinding
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.viewmodels.HomeViewModel
import io.jobtrends.jobtrends.viewmodels.HomeViewModel.HomeListKey.*
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), ActivityManager {

    enum class HomeState : ActivityState {
        TRAINING_STATE,
        JOB_STATE
    }

    companion object {
        private const val BOARDING: String = "BOARDING"
    }

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override var activityState: ActivityState = TRAINING_STATE

    init {
        App.component.inject(this)
        homeViewModel.registerActivityManager(this)
        val sharedPreferences = getDefaultSharedPreferences(App.app.applicationContext)
        val editor = sharedPreferences.edit()
        editor.putBoolean(BOARDING, false)
        editor.apply()
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun build() {
        val cls = when (activityState) {
            TRAINING_STATE -> CurriculumActivity::class.java
            JOB_STATE -> JobActivity::class.java
            else -> TODO()
        }
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun onBackPressed() {}

    override fun getState(): ActivityState = activityState

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding: ActivityHomeBinding = setContentView(this, activity_home)
        activityHomeBinding.homeViewModel = homeViewModel

        buildActionBar()
        buildRecyclers()
    }

    private fun buildRecyclers() {
        recycler_0.adapter = RecyclerAdapter(homeViewModel, surface_home, MOST_JOBS_LIST_KEY)
        recycler_1.adapter = RecyclerAdapter(homeViewModel, surface_home, LAST_JOBS_LIST_KEY)
    }

    private fun buildActionBar() {
        val inflater = from(this)
        val actionbarHomeBinding: ActionbarHomeBinding =
            inflate(inflater, actionbar_home, null, false)
        actionbarHomeBinding.searchJob = homeViewModel.searchJob

        val appCompatAutoCompleteTextView =
            actionbarHomeBinding.root.findViewById<AppCompatAutoCompleteTextView>(R.id.auto_complete_0)
        appCompatAutoCompleteTextView.setOnItemClickListener { _, _, index, _ ->
            onItemClickListener(index)
        }
        val adapter = AutoCompleteAdapter(this, homeViewModel, SEARCH_JOBS_LIST_KEY)
        appCompatAutoCompleteTextView.setAdapter(adapter)

        val layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setCustomView(actionbarHomeBinding.root, layoutParams)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    private fun onItemClickListener(index: Int) {
        val model = homeViewModel.getItem(SEARCH_JOBS_LIST_KEY, index) as JobModel
        homeViewModel.onClickJob(model)
    }
}

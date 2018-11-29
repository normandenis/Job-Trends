package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.ActionBar.LayoutParams
import android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.*
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActionbarHomeBinding
import io.jobtrends.jobtrends.databinding.ActivityHomeBinding
import io.jobtrends.jobtrends.viewmodels.HomeViewModel
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
    val jobSought: ObservableField<String>

    init {
        App.component.inject(this)
        jobSought = ObservableField("")
        homeViewModel.registerActivityManager(this)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.app.applicationContext)
        val editor = sharedPreferences.edit()
        editor.putBoolean(BOARDING, false)
        editor.apply()
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun getState(): ActivityState = activityState

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        activityHomeBinding.homeViewModel = homeViewModel

        val inflater = LayoutInflater.from(this)
        val binding: ActionbarHomeBinding = DataBindingUtil.inflate(inflater, R.layout.actionbar_home, null, false)
        binding.homeActivity = this

        val layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setCustomView(binding.root, layoutParams)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        picker_0.adapter = RecyclerAdapter(homeViewModel, R.layout.surface_home)
        picker_1.adapter = RecyclerAdapter(homeViewModel, R.layout.surface_home)
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
}

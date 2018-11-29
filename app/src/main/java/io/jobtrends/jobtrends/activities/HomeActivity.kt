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
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActionbarHomeBinding
import io.jobtrends.jobtrends.managers.HomeManager
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), ActivityListener {

    enum class HomeState : State {
        TRAINING_STATE,
        JOB_STATE
    }

    companion object {
        private const val BOARDING = "BOARDING"
    }

    @Inject
    lateinit var homeManager: HomeManager

    override var state: State = HomeState.TRAINING_STATE

    val jobSought: ObservableField<String>

    init {
        App.component.inject(this)
        jobSought = ObservableField("")
        homeManager.registerActivityListener(this)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.app.applicationContext)
        val editor = sharedPreferences.edit()
        editor.putBoolean(BOARDING, false)
        editor.apply()
    }

    override fun onSetState(state: State) {
        this.state = state
    }

    override fun onGetState(): State = state

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val inflater = LayoutInflater.from(this)
        val binding: ActionbarHomeBinding = DataBindingUtil.inflate(inflater, R.layout.actionbar_home, null, false)
        binding.homeActivity = this

        val layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setCustomView(binding.root, layoutParams)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        picker_0.adapter = RecyclerAdapter(homeManager, R.layout.surface_home)
        picker_1.adapter = RecyclerAdapter(homeManager, R.layout.surface_home)
    }

    override fun onNavNext() {
        val cls = when (state) {
            HomeState.TRAINING_STATE -> TrainingActivity::class.java
            HomeState.JOB_STATE -> JobActivity::class.java
            else -> TODO()
        }
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun onNavBack() {}

    override fun onBackPressed() {}
}

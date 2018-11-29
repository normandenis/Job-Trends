package io.jobtrends.jobtrends.activities

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.*
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.fragments.*
import io.jobtrends.jobtrends.viewmodels.ExperienceViewModel
import io.jobtrends.jobtrends.viewmodels.PassionViewModel
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class CurriculumActivity : AppCompatActivity(), ActivityManager {

    enum class TrainingActivityState : ActivityState {
        HOME_STATE,
        TRAINING_STATE,
        EXPERIENCE_STATE,
        PASSION_STATE
    }

    @Inject
    lateinit var trainingViewModel: TrainingViewModel
    @Inject
    lateinit var experienceViewModel: ExperienceViewModel
    @Inject
    lateinit var passionViewModel: PassionViewModel
    override var activityState: ActivityState = TRAINING_STATE

    init {
        App.component.inject(this)
        trainingViewModel.registerActivityManager(this)
        experienceViewModel.registerActivityManager(this)
        passionViewModel.registerActivityManager(this)
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        build()
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun getState(): ActivityState = activityState

    override fun build() {
        val fragment = when (activityState) {
            TRAINING_STATE -> {
                supportActionBar?.title = "Formations"
                when (trainingViewModel.getCount()) {
                    0 -> TrainingEmptyFragment()
                    else -> TrainingFragment()
                }
            }
            EXPERIENCE_STATE -> {
                supportActionBar?.title = "Expériences"
                when (experienceViewModel.getCount()) {
                    0 -> ExperienceEmptyFragment()
                    else -> ExperienceFragment()
                }
            }
            PASSION_STATE -> {
                supportActionBar?.title = "Expériences"
                when (passionViewModel.getCount()) {
                    0 -> PassionEmptyFragment()
                    else -> PassionFragment()
                }
            }
            else -> {
                finish()
                Fragment()
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frame_0.id, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        activityState = when (activityState) {
            TRAINING_STATE -> HOME_STATE
            EXPERIENCE_STATE -> TRAINING_STATE
            PASSION_STATE -> EXPERIENCE_STATE
            else -> TODO()
        }
        build()
        return true
    }
}

package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.CurriculumActivity.CurriculumState.*
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.fragments.*
import io.jobtrends.jobtrends.viewmodels.ExperienceViewModel
import io.jobtrends.jobtrends.viewmodels.ExperienceViewModel.ExperienceListKey.EXPERIENCE_LIST_KEY
import io.jobtrends.jobtrends.viewmodels.SkillViewModel
import io.jobtrends.jobtrends.viewmodels.SkillViewModel.SkillListKey.SKILL_LIST_KEY
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel.TrainingListKey.TRAINING_LIST_KEY
import io.jobtrends.jobtrends.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class CurriculumActivity : AppCompatActivity(), ActivityManager {

    enum class CurriculumState : ActivityState {
        HOME_STATE,
        USER_STATE,
        TRAINING_STATE,
        EXPERIENCE_STATE,
        PASSION_STATE,
        RESULT_STATE
    }

    @Inject
    lateinit var userViewModel: UserViewModel
    @Inject
    lateinit var trainingViewModel: TrainingViewModel
    @Inject
    lateinit var experienceViewModel: ExperienceViewModel
    @Inject
    lateinit var skillViewModel: SkillViewModel
    override var activityState: ActivityState = USER_STATE

    init {
        App.component.inject(this)
        userViewModel.registerActivityManager(this)
        trainingViewModel.registerActivityManager(this)
        experienceViewModel.registerActivityManager(this)
        skillViewModel.registerActivityManager(this)
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
        val fragment: Fragment? = when (activityState) {
            HOME_STATE -> null
            USER_STATE -> {
                supportActionBar?.title = "Informations"
                UserFragment()
            }
            TRAINING_STATE -> {
                supportActionBar?.title = "Formations"
                when (trainingViewModel.getCount(TRAINING_LIST_KEY)) {
                    0 -> TrainingEmptyFragment()
                    else -> TrainingFragment()
                }
            }
            EXPERIENCE_STATE -> {
                supportActionBar?.title = "Expériences"
                when (experienceViewModel.getCount(EXPERIENCE_LIST_KEY)) {
                    0 -> ExperienceEmptyFragment()
                    else -> ExperienceFragment()
                }
            }
            PASSION_STATE -> {
                supportActionBar?.title = "Compétences"
                when (skillViewModel.getCount(SKILL_LIST_KEY)) {
                    0 -> SkillEmptyFragment()
                    else -> SkillFragment()
                }
            }
            RESULT_STATE -> {
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                null
            }
            else -> TODO()
        }
        if (fragment == null) {
            finish()
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frame_0.id, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        activityState = when (activityState) {
            USER_STATE -> HOME_STATE
            TRAINING_STATE -> USER_STATE
            EXPERIENCE_STATE -> TRAINING_STATE
            PASSION_STATE -> EXPERIENCE_STATE
            RESULT_STATE -> PASSION_STATE
            else -> TODO()
        }
        build()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        activityState = when (activityState) {
            USER_STATE -> HOME_STATE
            TRAINING_STATE -> USER_STATE
            EXPERIENCE_STATE -> TRAINING_STATE
            PASSION_STATE -> EXPERIENCE_STATE
            RESULT_STATE -> PASSION_STATE
            else -> TODO()
        }
        build()
        return true
    }
}

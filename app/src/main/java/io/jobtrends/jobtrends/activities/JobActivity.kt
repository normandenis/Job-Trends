package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.JobActivity.JobState.*
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityJobBinding
import io.jobtrends.jobtrends.viewmodels.JobViewModel
import io.jobtrends.jobtrends.models.JobModel
import kotlinx.android.synthetic.main.activity_job.*
import javax.inject.Inject

class JobActivity : AppCompatActivity(), ActivityManager {

    enum class JobState : ActivityState {
        TRAINING_STATE
    }

    @Inject
    lateinit var jobViewModel: JobViewModel

    @Inject
    lateinit var jobModel: JobModel

    override var activityState: ActivityState = TRAINING_STATE

    init {
        App.component.inject(this)
        jobViewModel.registerActivityManager(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJobBinding = DataBindingUtil.setContentView(this, R.layout.activity_job)
        binding.jobModel = jobModel
        binding.jobViewModel = jobViewModel
        supportActionBar?.title = jobModel.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        picker_0.adapter = RecyclerAdapter(jobViewModel, R.layout.surface_job)
        picker_0.scrollToPosition(1)
    }

    override fun build() {
        val cls = when (activityState) {
            TRAINING_STATE -> CurriculumActivity::class.java
            else -> TODO()
        }
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun getState(): ActivityState = activityState

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}

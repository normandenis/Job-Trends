package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper.wrap
import io.jobtrends.jobtrends.R.layout.activity_job
import io.jobtrends.jobtrends.R.layout.surface_job
import io.jobtrends.jobtrends.activities.JobActivity.JobState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityJobBinding
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.viewmodels.JobViewModel
import io.jobtrends.jobtrends.viewmodels.JobViewModel.JobListKey.STATISTICS_LIST_KEY
import kotlinx.android.synthetic.main.activity_job.*
import javax.inject.Inject

class JobActivity : AppCompatActivity(), ActivityManager {

    enum class JobState : ActivityState {
        TRAINING_STATE
    }

    @Inject
    lateinit var jobViewModel: JobViewModel
    override var activityState: ActivityState = TRAINING_STATE

    init {
        App.component.inject(this)
        jobViewModel.registerActivityManager(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJobBinding = setContentView(this, activity_job)
        binding.jobModel = jobViewModel.jobModel
        binding.jobViewModel = jobViewModel
        supportActionBar?.title = jobViewModel.jobModel.get()?.source?.title?.get()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recycler_0.adapter = RecyclerAdapter(jobViewModel, surface_job, STATISTICS_LIST_KEY)
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

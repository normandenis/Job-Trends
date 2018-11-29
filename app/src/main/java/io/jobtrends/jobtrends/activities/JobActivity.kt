package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityJobBinding
import io.jobtrends.jobtrends.managers.JobManager
import io.jobtrends.jobtrends.models.JobModel
import kotlinx.android.synthetic.main.activity_job.*
import javax.inject.Inject

class JobActivity : AppCompatActivity(), ActivityListener {

    enum class JobState : State {
        TRAINING_STATE
    }

    @Inject
    lateinit var jobManager: JobManager

    @Inject
    lateinit var jobModel: JobModel

    override var state: State = JobState.TRAINING_STATE

    init {
        App.component.inject(this)
        jobManager.registerActivityListener(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJobBinding = DataBindingUtil.setContentView(this, R.layout.activity_job)
        binding.jobModel = jobModel
        binding.jobManager = jobManager

        supportActionBar?.title = jobModel.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        picker_0.adapter = RecyclerAdapter(jobManager, R.layout.surface_job)
        picker_0.scrollToPosition(1)
    }

    override fun onNavNext() {
        val cls = when (state) {
            JobState.TRAINING_STATE -> TrainingActivity::class.java
            else -> TODO()
        }
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun onSetState(state: State) {
        this.state = state
    }

    override fun onGetState(): State = state

    override fun onNavBack() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}

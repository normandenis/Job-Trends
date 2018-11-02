package io.jobtrends.jobtrends.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityJobBinding
import io.jobtrends.jobtrends.models.JobModel
import javax.inject.Inject

class JobActivity : AppCompatActivity() {

    @Inject
    lateinit var jobModel: JobModel

    init {
        App.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityJobBinding = DataBindingUtil.setContentView(this, R.layout.activity_job)
        binding.jobModel = jobModel
    }
}

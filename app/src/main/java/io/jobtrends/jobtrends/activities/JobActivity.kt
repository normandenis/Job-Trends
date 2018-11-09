package io.jobtrends.jobtrends.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityJobBinding
import io.jobtrends.jobtrends.models.JobModel
import kotlinx.android.synthetic.main.activity_home.*
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

        supportActionBar?.title = jobModel.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        picker_0.adapter = RecyclerAdapter(R.layout.fragment_job)
        picker_0.scrollToPosition(1)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.finish()
        return true
    }
}

package io.jobtrends.jobtrends.managers

import android.content.Context
import android.content.Intent
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.JobActivity
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Inject

class HomeManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var wrapper: Wrapper<JobModel>

    @Inject
    lateinit var context: Context

    private val jobModelArray: Array<JobModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_home)
        jobModelArray = jsonManager.deserialize(data)
    }

    fun getItem(index: Int): JobModel {
        return jobModelArray[index]
    }

    fun getCount(): Int {
        return jobModelArray.size
    }

    fun onClickJob(jobModel: JobModel) {
        wrapper.obj = jobModel
        val intent = Intent(context, JobActivity::class.java)
        context.startActivity(intent)
    }
}
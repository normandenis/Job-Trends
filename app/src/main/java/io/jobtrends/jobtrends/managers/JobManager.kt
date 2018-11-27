package io.jobtrends.jobtrends.managers

import android.content.Context
import android.content.Intent
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.TrainingActivity
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.JobStatisticModel
import javax.inject.Inject

class JobManager : RecyclerManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var context: Context

    private val jobStatisticModelArray: Array<JobStatisticModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_job)
        jobStatisticModelArray = jsonManager.deserialize(data)
    }

    override fun getItem(index: Int): Any {
        return jobStatisticModelArray[index]
    }

    override fun getCount(): Int {
        return jobStatisticModelArray.size
    }

    fun onClick(context: Context) {
        val intent = Intent(context, TrainingActivity::class.java)
        context.startActivity(intent)
    }
}
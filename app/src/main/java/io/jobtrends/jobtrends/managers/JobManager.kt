package io.jobtrends.jobtrends.managers

import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityListener
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.JobStatisticModel
import javax.inject.Inject

class JobManager : IManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    private var activityListener: ActivityListener? = null

    private val jobStatisticModelArray: Array<JobStatisticModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_job)
        jobStatisticModelArray = jsonManager.deserialize(data)
    }

    override fun registerActivityListener(activityListener: ActivityListener) {
        this.activityListener = activityListener
    }

    override fun unregisterActivityListener() {
        activityListener = null
    }

    override fun getItem(index: Int): Any {
        return jobStatisticModelArray[index]
    }

    override fun getCount(): Int {
        return jobStatisticModelArray.size
    }

    fun onClick() {
        activityListener?.onNavNext()
    }
}
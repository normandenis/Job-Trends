package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.JobStatisticModel
import javax.inject.Inject

class JobViewModel : ViewModel {
    @Inject
    lateinit var rawManager: RawManager
    @Inject
    lateinit var jsonManager: JsonManager
    private var activityManager: ActivityManager? = null
    private val jobStatisticModelArray: Array<JobStatisticModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_job)
        jobStatisticModelArray = jsonManager.deserialize(data)
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun getItem(index: Int): Any {
        return jobStatisticModelArray[index]
    }

    override fun getCount(): Int {
        return jobStatisticModelArray.size
    }

    fun onClick() {
        activityManager?.build()
    }
}

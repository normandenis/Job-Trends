package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.JOB_STATE
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.TRAINING_STATE
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Inject

class HomeViewModel : ViewModel {
    @Inject
    lateinit var rawManager: RawManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var wrapper: Wrapper
    private var activityManager: ActivityManager? = null
    private val jobModelArray: Array<JobModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_home)
        jobModelArray = jsonManager.deserialize(data)
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun getItem(index: Int): Any {
        return jobModelArray[index]
    }

    override fun getCount(): Int {
        return jobModelArray.size
    }

    fun onClickAnalyse() {
        activityManager?.setState(TRAINING_STATE)
        activityManager?.build()
    }

    fun onClickJob(jobModel: JobModel) {
        wrapper.register(jobModel, true)
        activityManager?.setState(JOB_STATE)
        activityManager?.build()
    }
}
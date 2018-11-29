package io.jobtrends.jobtrends.managers

import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityListener
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.JOB_STATE
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Inject

class HomeManager : IManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var wrapper: Wrapper

    private var activityListener: ActivityListener? = null


    private val jobModelArray: Array<JobModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_home)
        jobModelArray = jsonManager.deserialize(data)
    }

    override fun registerActivityListener(activityListener: ActivityListener) {
        this.activityListener = activityListener
    }

    override fun unregisterActivityListener() {
        activityListener = null
    }

    override fun getItem(index: Int): Any {
        return jobModelArray[index]
    }

    override fun getCount(): Int {
        return jobModelArray.size
    }

    fun onClickJob(jobModel: JobModel) {
        wrapper.register(jobModel, true)
        activityListener?.onSetState(JOB_STATE)
        activityListener?.onNavNext()
    }
}
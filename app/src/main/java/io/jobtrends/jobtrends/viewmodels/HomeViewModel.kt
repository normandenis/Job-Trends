package io.jobtrends.jobtrends.viewmodels

import com.android.volley.Request.Method.GET
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.JOB_STATE
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.TRAINING_STATE
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.viewmodels.HomeViewModel.HomeListKey.LAST_JOBS_LIST_KEY
import io.jobtrends.jobtrends.viewmodels.HomeViewModel.HomeListKey.MOST_JOBS_LIST_KEY
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Inject


class HomeViewModel : ViewModel {

    companion object {
        private const val JOBS = "analysis/jobs/"
        private const val MOST_JOBS = "$JOBS?_type=most"
        private const val LAST_JOBS = "$JOBS?_type=last"
    }

    enum class HomeListKey : ListKey {
        MOST_JOBS_LIST_KEY,
        LAST_JOBS_LIST_KEY
    }

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var wrapper: Wrapper
    private var activityManager: ActivityManager? = null
    override var container: MutableMap<ListKey, MutableList<Model>> = mutableMapOf()

    init {
        App.component.inject(this)
        refresh()
    }

    private fun refresh() {
        apiManager.request(GET, MOST_JOBS, ::jobsMostCallback)
        apiManager.request(GET, LAST_JOBS, ::jobsLastCallback)
    }

    private fun jobsMostCallback(statusCode: Int, data: String) {
        container[MOST_JOBS_LIST_KEY] = jsonManager.deserialize<Array<JobModel>>(data).toMutableList() as MutableList<Model>
    }

    private fun jobsLastCallback(statusCode: Int, data: String) {
        container[LAST_JOBS_LIST_KEY] = jsonManager.deserialize<Array<JobModel>>(data).toMutableList() as MutableList<Model>
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return container[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return container[key]!!.size
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
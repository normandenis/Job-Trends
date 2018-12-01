package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.JobViewModel.JobListKey.STATISTICS_LIST_KEY
import javax.inject.Inject

class JobViewModel : ViewModel {

    enum class JobListKey : ListKey {
        STATISTICS_LIST_KEY
    }

    @Inject
    lateinit var jobModel: JobModel
    private var activityManager: ActivityManager? = null
    override var container: MutableMap<ListKey, MutableList<Model>> = mutableMapOf()

    init {
        App.component.inject(this)
        container[STATISTICS_LIST_KEY] = jobModel.statistics.toMutableList() as MutableList<Model>
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

    fun onClick() {
        activityManager?.build()
    }
}

package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.orhanobut.logger.Logger
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.StatisticModel
import io.jobtrends.jobtrends.viewmodels.JobViewModel.JobListKey.STATISTICS_LIST_KEY
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class JobViewModel : ViewModel {

    enum class JobListKey : ListKey {
        STATISTICS_LIST_KEY
    }

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    var jobModel: ObservableField<JobModel> = ObservableField(JobModel())

    init {
        App.component.inject(this)
        lists[STATISTICS_LIST_KEY] = ObservableArrayList<StatisticModel>() as ObservableList<Model>
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]?.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    fun jobCallback(statusCode: Int, data: String) {
        Logger.d(statusCode)
        Logger.json(data)
        val jobModel: JobModel = jsonManager.deserialize(data)
        this.jobModel.set(jobModel)
        lists[STATISTICS_LIST_KEY]?.clear()
        lists[STATISTICS_LIST_KEY]?.addAll(jobModel.statistics)
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]?.size ?: 0
    }

    fun onClick() {
        activity.build()
    }
}

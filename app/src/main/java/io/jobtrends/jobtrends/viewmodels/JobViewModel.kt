package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.android.volley.Request.Method.POST
import com.orhanobut.logger.Logger.d
import com.orhanobut.logger.Logger.json
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.JobActivity
import io.jobtrends.jobtrends.activities.JobActivity.JobState.CURRICULUM_STATE
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

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var userViewModel: UserViewModel
    @Inject
    lateinit var trainingViewModel: TrainingViewModel
    @Inject
    lateinit var experienceViewModel: ExperienceViewModel
    @Inject
    lateinit var skillViewModel: SkillViewModel

    val jobModel: ObservableField<JobModel> = ObservableField(JobModel())

    init {
        App.component.inject(this)
        lists[STATISTICS_LIST_KEY] = ObservableArrayList<StatisticModel>() as ObservableList<Model>
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]!!.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    fun jobCallback(statusCode: Int, data: String) {
        d(statusCode)
        json(data)
        if (statusCode != 200) {
            return
        }
        val jobModel: JobModel = jsonManager.deserialize(data)
        this.jobModel.set(jobModel)
        lists[STATISTICS_LIST_KEY]!!.clear()
        lists[STATISTICS_LIST_KEY]!!.addAll(jobModel.statistics)
        (activity as JobActivity).supportActionBar!!.title = jobModel.source.title.get()
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }

    fun onClickAnalyse() {
        apiManager.request(POST, "", { statusCode, data ->
            d(statusCode)
            json(data)
            userViewModel.startAnalysisCallback(statusCode, data)
            trainingViewModel.startAnalysisCallback(statusCode, data)
            experienceViewModel.startAnalysisCallback(statusCode, data)
            skillViewModel.startAnalysisCallback(statusCode, data)
        })
        activity.setState(CURRICULUM_STATE)
        activity.build()
    }
}

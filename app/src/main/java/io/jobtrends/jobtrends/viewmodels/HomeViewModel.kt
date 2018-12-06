package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import com.orhanobut.logger.Logger.d
import com.orhanobut.logger.Logger.json
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.CURRICULUM_STATE
import io.jobtrends.jobtrends.activities.HomeActivity.HomeState.JOB_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.HomeViewModel.HomeListKey.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class HomeViewModel : ViewModel {

    enum class HomeListKey : ListKey {
        SEARCH_JOBS_LIST_KEY,
        MOST_JOBS_LIST_KEY,
        LAST_JOBS_LIST_KEY
    }

    companion object {
        private const val JOBS_URL = "jobs/"
        private const val MOST_JOBS_URL = "$JOBS_URL?_type=most"
        private const val LAST_JOBS_URL = "$JOBS_URL?_type=last"
        private const val SEARCH_JOBS_URL = "list?_type=job&_title="
    }

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var jobViewModel: JobViewModel
    @Inject
    lateinit var userViewModel: UserViewModel
    @Inject
    lateinit var trainingViewModel: TrainingViewModel
    @Inject
    lateinit var experienceViewModel: ExperienceViewModel
    @Inject
    lateinit var skillViewModel: SkillViewModel

    override lateinit var activity: ActivityManager
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()

    val searchJob: ObservableField<String> = ObservableField("")

    init {
        App.component.inject(this)
        lists[SEARCH_JOBS_LIST_KEY] = ObservableArrayList<JobModel>() as ObservableList<Model>
        lists[MOST_JOBS_LIST_KEY] = ObservableArrayList<JobModel>() as ObservableList<Model>
        lists[LAST_JOBS_LIST_KEY] = ObservableArrayList<JobModel>() as ObservableList<Model>
        refresh()
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]!!.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }

    private fun refresh() {
        apiManager.request(GET, MOST_JOBS_URL, { statusCode, data ->
            jobsCallback(MOST_JOBS_LIST_KEY, statusCode, data)
        })
        apiManager.request(GET, LAST_JOBS_URL, { statusCode, data ->
            jobsCallback(LAST_JOBS_LIST_KEY, statusCode, data)
        })
    }

    fun searchJob() {
        apiManager.request(GET, SEARCH_JOBS_URL + searchJob.get(), { statusCode, data ->
            jobsCallback(SEARCH_JOBS_LIST_KEY, statusCode, data)
        })
    }

    private fun jobsCallback(key: ListKey, statusCode: Int, data: String) {
        d(statusCode)
        json(data)
        if (statusCode != 200) {
            return
        }
        val jobs = jsonManager.deserialize<Array<JobModel>>(data)
        lists[key]!!.clear()
        lists[key]!!.addAll(jobs)
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

    fun onClickJob(jobModel: JobModel) {
        d("URL: $JOBS_URL${jobModel.id.get()}")
        apiManager.request(GET, JOBS_URL + jobModel.id.get(),
            { statusCode, data ->
                jobViewModel.jobCallback(statusCode, data)
            })
        activity.setState(JOB_STATE)
        activity.build()
    }
}
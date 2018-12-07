package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import com.orhanobut.logger.Logger.d
import com.orhanobut.logger.Logger.json
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.CurriculumState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AnalysisModel
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.UserModel
import io.jobtrends.jobtrends.viewmodels.UserViewModel.UserListKey.SEARCH_JOBS_LIST_KEY
import javax.inject.Inject

class UserViewModel : ViewModel, AutoCompleteListener {

    enum class UserListKey : ListKey {
        SEARCH_JOBS_LIST_KEY
    }

    companion object {
        private const val PROFILE_URL: String = "/profile"
        private const val SEARCH_JOBS_URL = "list?_type=job&_title="
    }

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var userModel: UserModel

    private lateinit var analysisModel: AnalysisModel

    init {
        App.component.inject(this)
        lists[SEARCH_JOBS_LIST_KEY] = ObservableArrayList<JobModel>() as ObservableList<Model>
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int = lists[key]!!.size

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]!!.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun searchJob() {
        apiManager.request(GET, SEARCH_JOBS_URL + userModel.job.get(), { statusCode, data ->
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


    private fun addProfileCallback(statusCode: Int, data: String) {
        d(statusCode)
        json(data)
        if (statusCode != 200) {
            return
        }
    }

    fun startAnalysisCallback(statusCode: Int, data: String) {
        if (statusCode != 200) {
            return
        }
        analysisModel = jsonManager.deserialize(data)
    }

    fun onClickNext() {
        userModel.birthday.set(1900.0)
        val json = jsonManager.serialize(userModel)
        val url = "${analysisModel.id.get()}$PROFILE_URL"
        apiManager.request(POST, url, ::addProfileCallback, json)
        activity.setState(TRAINING_STATE)
        activity.build()
    }
}
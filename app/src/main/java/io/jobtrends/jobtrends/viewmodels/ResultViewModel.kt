package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.orhanobut.logger.Logger
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AlternativeModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.ResultModel
import io.jobtrends.jobtrends.viewmodels.ResultViewModel.ResultListKey.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ResultViewModel : ViewModel {

    enum class ResultListKey : ListKey {
        NEAR_JOBS_LIST_KEY,
        POSSIBLE_JOBS_LIST_KEY,
        FORMATIONS_LIST_KEY
    }

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager

    private lateinit var resultModel: ResultModel

    init {
        App.component.inject(this)
        lists[NEAR_JOBS_LIST_KEY] = ObservableArrayList<AlternativeModel>() as ObservableList<Model>
        lists[POSSIBLE_JOBS_LIST_KEY] =
                ObservableArrayList<AlternativeModel>() as ObservableList<Model>
        lists[FORMATIONS_LIST_KEY] =
                ObservableArrayList<AlternativeModel>() as ObservableList<Model>
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]!!.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    fun resultCallback(statusCode: Int, data: String) {
        Logger.d(statusCode)
        Logger.json(data)
        if (statusCode != 200) {
            return
        }
        resultModel = jsonManager.deserialize(data)
        lists[NEAR_JOBS_LIST_KEY]!!.clear()
        lists[NEAR_JOBS_LIST_KEY]!!.addAll(resultModel.nears)
        lists[POSSIBLE_JOBS_LIST_KEY]!!.clear()
        lists[POSSIBLE_JOBS_LIST_KEY]!!.addAll(resultModel.possibles)
        lists[FORMATIONS_LIST_KEY]!!.clear()
        lists[FORMATIONS_LIST_KEY]!!.addAll(resultModel.possibles)
    }
}
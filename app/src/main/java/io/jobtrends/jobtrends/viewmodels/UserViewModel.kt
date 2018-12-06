package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableList
import com.android.volley.Request.Method.POST
import com.orhanobut.logger.Logger.d
import com.orhanobut.logger.Logger.json
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.CurriculumState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AnalysisModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.UserModel
import javax.inject.Inject

class UserViewModel : ViewModel {

    companion object {
        private const val PROFILE_URL: String = "/profile"
    }

    override lateinit var lists: MutableMap<ListKey, ObservableList<Model>>
    override lateinit var adapters: MutableMap<ListKey, AdapterManager>
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
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return userModel
    }

    override fun getCount(key: ListKey): Int = 1

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {}

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
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
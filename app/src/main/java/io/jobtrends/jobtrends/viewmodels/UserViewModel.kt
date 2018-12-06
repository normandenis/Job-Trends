package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableList
import com.android.volley.Request.Method.POST
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AnalaysisModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.UserModel
import javax.inject.Inject

class UserViewModel : ViewModel {

    companion object {
        private const val PROFILE_URL = "/profile"
    }

    override lateinit var lists: MutableMap<ListKey, ObservableList<Model>>
    override lateinit var adapters: MutableMap<ListKey, AdapterManager>
    override lateinit var activity: ActivityManager

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager

    private lateinit var analaysisModel: AnalaysisModel
    lateinit var userModel: UserModel

    init {
        App.component.inject(this)
    }

    override fun getItem(key: ListKey, index: Int): Model {
        userModel.birthday.get().toString()
        return userModel
    }

    override fun getCount(key: ListKey): Int = 1

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {}

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    private fun addProfileCallback(statusCode: Int, data: String) {}

    fun startAnalysisCallback(statusCode: Int, data: String) {
        analaysisModel = jsonManager.deserialize(data)
    }

    fun onClickNext() {
        val json = jsonManager.serialize(userModel)
        apiManager.request(POST, analaysisModel.id.get() + PROFILE_URL, ::addProfileCallback, json)
        activity.setState(TRAINING_STATE)
        activity.build()
    }
}
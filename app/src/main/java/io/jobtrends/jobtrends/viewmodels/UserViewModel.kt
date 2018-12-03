package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableList
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.TRAINING_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.UserModel

class UserViewModel : ViewModel {

    override lateinit var lists: MutableMap<ListKey, ObservableList<Model>>
    override lateinit var adapters: MutableMap<ListKey, AdapterManager>
    override lateinit var activity: ActivityManager

    val userModel: UserModel = UserModel()

    override fun getItem(key: ListKey, index: Int): Model {
        userModel.birthday.get().toString()
        return userModel
    }

    override fun getCount(key: ListKey): Int = 1

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {}

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    fun onClickNext() {
        activity.setState(TRAINING_STATE)
        activity.build()
    }
}
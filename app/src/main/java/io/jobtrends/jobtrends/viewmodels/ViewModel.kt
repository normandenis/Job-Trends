package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableList
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.models.Model

interface ListKey

interface ViewModel {
    var lists: MutableMap<ListKey, ObservableList<Model>>
    var adapters: MutableMap<ListKey, AdapterManager>
    var activity: ActivityManager

    fun getItem(key: ListKey, index: Int): Model
    fun getCount(key: ListKey): Int
    fun registerAdapterManager(key: ListKey, adapter: AdapterManager)
    fun registerActivityManager(activity: ActivityManager)
}

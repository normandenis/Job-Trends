package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.models.Model

interface ListKey

interface ViewModel {
    var container: MutableMap<ListKey, MutableList<Model>>
    fun getItem(key: ListKey, index: Int): Model
    fun getCount(key: ListKey): Int
    fun registerActivityManager(activityManager: ActivityManager)
    fun unregisterActivityManager()
}

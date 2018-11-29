package io.jobtrends.jobtrends.viewmodels

import io.jobtrends.jobtrends.activities.ActivityManager

interface ViewModel {
    fun getItem(index: Int): Any
    fun getCount(): Int
    fun registerActivityManager(activityManager: ActivityManager)
    fun unregisterActivityManager()
}

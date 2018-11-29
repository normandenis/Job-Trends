package io.jobtrends.jobtrends.managers

import io.jobtrends.jobtrends.activities.ActivityListener

interface IManager {

    fun getItem(index: Int): Any

    fun getCount(): Int

    fun registerActivityListener(activityListener: ActivityListener)

    fun unregisterActivityListener()
}
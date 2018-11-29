package io.jobtrends.jobtrends.activities

interface ActivityManager {

    var activityState: ActivityState

    fun build()

    fun setState(activityState: ActivityState)

    fun getState(): ActivityState

}


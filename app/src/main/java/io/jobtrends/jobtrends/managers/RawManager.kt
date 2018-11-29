package io.jobtrends.jobtrends.managers

import android.content.res.Resources
import io.jobtrends.jobtrends.dagger.App
import javax.inject.Inject

class RawManager {
    @Inject
    lateinit var resources: Resources

    init {
        App.component.inject(this)
    }

    fun readRaw(raw: Int): String {
        return try {
            resources.openRawResource(raw).bufferedReader().use { it.readText() }
        } catch (exception: Exception) {
            ""
        }
    }
}

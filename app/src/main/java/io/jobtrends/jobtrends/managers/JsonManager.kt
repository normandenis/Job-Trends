package io.jobtrends.jobtrends.managers

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.jobtrends.jobtrends.dagger.App
import javax.inject.Inject

class JsonManager {

    @Inject
    lateinit var gson: Gson

    init {
        App.component.inject(this)
    }

    inline fun <reified T> deserialize(str: String): T {
        return try {
            gson.fromJson(str, T::class.java)
        } catch (exception: JsonSyntaxException) {
            T::class.java.newInstance()
        }
    }

    fun <T> serialize(obj: T): String {
        return try {
            gson.toJson(obj)
        } catch (exception: JsonSyntaxException) {
            ""
        }
    }
}

package io.jobtrends.jobtrends.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Statistic
/**
 *
 * @param value
 * @param key
 */(
    @SerializedName("key")
    @Expose var key: String = "",
    @SerializedName("value")
    @Expose var value: String = ""
) : Model

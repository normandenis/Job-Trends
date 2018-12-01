package io.jobtrends.jobtrends.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source
/**
 *
 * @param title
 * @param count
 * @param lastUpdated
 * @param code
 */(
    @SerializedName("last_updated")
    @Expose var lastUpdated: Long = 0,
    @SerializedName("code")
    @Expose var code: String = "",
    @SerializedName("count")
    @Expose var count: Long = 0,
    @SerializedName("title")
    @Expose var title: String = ""
) : Model

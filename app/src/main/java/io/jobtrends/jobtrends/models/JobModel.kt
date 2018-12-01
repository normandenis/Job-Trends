package io.jobtrends.jobtrends.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JobModel
/**
 *
 * @param id
 * @param index
 * @param source
 * @param type
 * @param statistics
 * @param found
 * @param version
 */(
    @SerializedName("_index")
    @Expose var index: String = "",
    @SerializedName("_type")
    @Expose var type: String = "",
    @SerializedName("_id")
    @Expose var id: String = "",
    @SerializedName("_version")
    @Expose var version: Long = 0,
    @SerializedName("found")
    @Expose var found: Boolean = false,
    @SerializedName("_source")
    @Expose var source: Source = Source(),
    @SerializedName("_statistics")
    @Expose var statistics: List<Statistic> = arrayListOf()
) : Model


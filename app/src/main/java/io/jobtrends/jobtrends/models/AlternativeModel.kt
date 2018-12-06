package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param key
 * @param value
 */
data class AlternativeModel(

    @SerializedName("title")
    @JsonAdapter(ObservableStringAdapter::class)
    var key: ObservableField<String> = ObservableField(""),

    @SerializedName("code", alternate = ["link"])
    @JsonAdapter(ObservableStringAdapter::class)
    var value: ObservableField<String> = ObservableField("")

) : Model

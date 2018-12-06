package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableDoubleAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param value
 * @param key
 */
data class StatisticModel(

    @SerializedName("key")
    @JsonAdapter(ObservableStringAdapter::class)
    var key: ObservableField<String> = ObservableField(""),

    @SerializedName("value")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var value: ObservableField<Double> = ObservableField(0.0)

) : Model

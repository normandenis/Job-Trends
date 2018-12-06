package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableDoubleAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param school
 * @param degree
 * @param topic
 * @param from
 * @param to
 */
data class TrainingModel(

    @SerializedName("school")
    @JsonAdapter(ObservableStringAdapter::class)
    var school: ObservableField<String> = ObservableField(""),

    @SerializedName("degree")
    @JsonAdapter(ObservableStringAdapter::class)
    var degree: ObservableField<String> = ObservableField(""),

    @SerializedName("topic")
    @JsonAdapter(ObservableStringAdapter::class)
    var topic: ObservableField<String> = ObservableField(""),

    @SerializedName("from")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var from: ObservableField<Double> = ObservableField(0.0),

    @SerializedName("to")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var to: ObservableField<Double> = ObservableField(0.0)

) : Model

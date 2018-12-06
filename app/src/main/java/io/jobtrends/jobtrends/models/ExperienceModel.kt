package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableDoubleAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param job
 * @param company
 * @param place
 * @param from
 * @param to
 */
data class ExperienceModel(

    @SerializedName("job")
    @JsonAdapter(ObservableStringAdapter::class)
    var job: ObservableField<String> = ObservableField(""),

    @SerializedName("company")
    @JsonAdapter(ObservableStringAdapter::class)
    var company: ObservableField<String> = ObservableField(""),

    @SerializedName("place")
    @JsonAdapter(ObservableStringAdapter::class)
    var place: ObservableField<String> = ObservableField(""),

    @SerializedName("from")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var from: ObservableField<Double> = ObservableField(0.0),

    @SerializedName("to")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var to: ObservableField<Double> = ObservableField(0.0)

) : Model

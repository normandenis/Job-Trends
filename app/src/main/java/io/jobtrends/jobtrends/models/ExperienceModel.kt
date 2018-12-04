package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import io.jobtrends.jobtrends.adapters.ObservableLongAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param job
 * @param company
 * @param place
 * @param from
 * @param to
 */
data class ExperienceModel(

    @JsonAdapter(ObservableStringAdapter::class)
    var job: ObservableField<String> = ObservableField(""),

    @JsonAdapter(ObservableStringAdapter::class)
    var company: ObservableField<String> = ObservableField(""),

    @JsonAdapter(ObservableStringAdapter::class)
    var place: ObservableField<String> = ObservableField(""),

    @JsonAdapter(ObservableLongAdapter::class)
    var from: ObservableField<Long> = ObservableField(0),

    @JsonAdapter(ObservableLongAdapter::class)
    var to: ObservableField<Long> = ObservableField(0)

) : Model

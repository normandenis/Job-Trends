package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import io.jobtrends.jobtrends.adapters.ObservableDoubleToStringAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param name
 * @param rating
 */
data class SkillModel(

    @JsonAdapter(ObservableStringAdapter::class)
    var name: ObservableField<String> = ObservableField(""),

    @JsonAdapter(ObservableDoubleToStringAdapter::class)
    var rating: ObservableField<Double> = ObservableField(2.5)

) : Model

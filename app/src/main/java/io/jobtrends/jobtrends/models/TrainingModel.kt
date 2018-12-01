package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

data class TrainingModel(@JsonAdapter(ObservableStringAdapter::class)
                         var school: ObservableField<String> = ObservableField(""),
                         @JsonAdapter(ObservableStringAdapter::class)
                         var diploma: ObservableField<String> = ObservableField(""),
                         @JsonAdapter(ObservableStringAdapter::class)
                         var activity: ObservableField<String> = ObservableField(""),
                         @JsonAdapter(ObservableStringAdapter::class)
                         var start: ObservableField<String> = ObservableField(""),
                         @JsonAdapter(ObservableStringAdapter::class)
                         var end: ObservableField<String> = ObservableField("")) : Model

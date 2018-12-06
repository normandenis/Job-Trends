package io.jobtrends.jobtrends.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

data class AnalaysisModel(
    @SerializedName("_id")
    @JsonAdapter(ObservableStringAdapter::class)
    var id: ObservableField<String> = ObservableField(),

    @SerializedName("createdAt")
    @JsonAdapter(ObservableStringAdapter::class)
    var createdAt: ObservableField<String> = ObservableField(),

    @SerializedName("educations")
    var educations: ObservableList<TrainingModel> = ObservableArrayList(),

    @SerializedName("experiences")
    var experiences: ObservableList<ExperienceModel> = ObservableArrayList(),

    @SerializedName("skills")
    var skills: ObservableList<SkillModel> = ObservableArrayList(),

    @SerializedName("status")
    @JsonAdapter(ObservableStringAdapter::class)
    var status: ObservableField<String> = ObservableField(),

    @SerializedName("updatedAt")
    @JsonAdapter(ObservableStringAdapter::class)
    var updatedAt: ObservableField<String> = ObservableField()

)
package io.jobtrends.jobtrends.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

data class AnalysisModel(
    @SerializedName("_id")
    @JsonAdapter(ObservableStringAdapter::class)
    var id: ObservableField<String> = ObservableField(),

    @SerializedName("createdAt")
    @JsonAdapter(ObservableStringAdapter::class)
    var createdAt: ObservableField<String> = ObservableField(),

    @SerializedName("educations")
    var educations: ObservableArrayList<TrainingModel> = ObservableArrayList(),

    @SerializedName("experiences")
    var experiences: ObservableArrayList<ExperienceModel> = ObservableArrayList(),

    @SerializedName("skills")
    var skills: ObservableArrayList<SkillModel> = ObservableArrayList(),

    @SerializedName("status")
    @JsonAdapter(ObservableStringAdapter::class)
    var status: ObservableField<String> = ObservableField(),

    @SerializedName("updatedAt")
    @JsonAdapter(ObservableStringAdapter::class)
    var updatedAt: ObservableField<String> = ObservableField()

) : Model
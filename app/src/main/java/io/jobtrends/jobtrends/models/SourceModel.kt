package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableDoubleAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param title
 * @param count
 * @param lastUpdated
 * @param code
 * @param hired
 * @param salary
 * @param projectedGrowth
 * @param searchPercentage
 */
data class SourceModel(

    // region lastUpdated

    @SerializedName("last_updated")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var lastUpdated: ObservableField<Double> = ObservableField(0.0),

    // endregion

    // region code

    @SerializedName("code")
    @JsonAdapter(ObservableStringAdapter::class)
    var code: ObservableField<String> = ObservableField(""),

    // endregion

    // region count

    @SerializedName("count")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var count: ObservableField<Double> = ObservableField(0.0),

    // endregion

    // region title

    @SerializedName("title")
    @JsonAdapter(ObservableStringAdapter::class)
    var title: ObservableField<String> = ObservableField(""),

    // endregion

    // region projectedGrowth

    @SerializedName("projectedGrowth")
    @JsonAdapter(ObservableStringAdapter::class)
    var projectedGrowth: ObservableField<String> = ObservableField(""),

    // endregion

    // region searchPercentage

    @SerializedName("searchPercentage")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var searchPercentage: ObservableField<Double> = ObservableField(0.0),

    // endregion

    // region searchPercentage

    @SerializedName("msalary")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var salary: ObservableField<Double> = ObservableField(0.0),

    // endregion

    // region searchPercentage

    @SerializedName("hired")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var hired: ObservableField<Double> = ObservableField(0.0)

// endregion


) : Model

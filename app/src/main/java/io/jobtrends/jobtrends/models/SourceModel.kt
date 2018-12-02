package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableLongAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param title
 * @param count
 * @param lastUpdated
 * @param code
 * @param projectedGrowth
 * @param searchPercentage
 */
data class SourceModel(

    // region lastUpdated

    @SerializedName("last_updated")
    @JsonAdapter(ObservableLongAdapter::class)
    var lastUpdated: ObservableField<Long> = ObservableField(0),

    // endregion

    // region code

    @SerializedName("code")
    @JsonAdapter(ObservableStringAdapter::class)
    var code: ObservableField<String> = ObservableField(""),

    // endregion

    // region count

    @SerializedName("count")
    @JsonAdapter(ObservableLongAdapter::class)
    var count: ObservableField<Long> = ObservableField(0),

    // endregion

    // region title

    @SerializedName("title")
    @JsonAdapter(ObservableStringAdapter::class)
    var title: ObservableField<String> = ObservableField(""),

    // endregion

    // region count

    @SerializedName("projectedGrowth")
    @JsonAdapter(ObservableLongAdapter::class)
    var projectedGrowth: ObservableField<Long> = ObservableField(0),

    // endregion

    // region count

    @SerializedName("searchPercentage")
    @JsonAdapter(ObservableLongAdapter::class)
    var searchPercentage: ObservableField<Long> = ObservableField(0)

    // endregion

) : Model

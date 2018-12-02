package io.jobtrends.jobtrends.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableBooleanAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param id
 * @param index
 * @param source
 * @param type
 * @param statistics
 * @param found
 * @param version
 */
data class JobModel(

    // region index

    @SerializedName("_index")
    @JsonAdapter(ObservableStringAdapter::class)
    var index: ObservableField<String> = ObservableField(""),

    // endregion

    // region type

    @SerializedName("_type")
    @JsonAdapter(ObservableStringAdapter::class)
    var type: ObservableField<String> = ObservableField(""),

    // endregion

    // region id

    @SerializedName("_id")
    @JsonAdapter(ObservableStringAdapter::class)
    var id: ObservableField<String> = ObservableField(""),

    // endregion

    // region version

    @SerializedName("_version")
    @JsonAdapter(ObservableStringAdapter::class)
    var version: ObservableField<Long> = ObservableField(0),

    // endregion

    // region found

    @SerializedName("found")
    @JsonAdapter(ObservableBooleanAdapter::class)
    var found: ObservableField<Boolean> = ObservableField(false),

    // endregion

    // region source

    @SerializedName("_source")
    var source: SourceModel = SourceModel(),

    // endregion

    // region statistics

    @SerializedName("_statistics")
    var statistics: ObservableArrayList<StatisticModel> = ObservableArrayList()

    // endregion

) : Model {
    override fun toString(): String {
        return source.title.get() ?: ""
    }
}

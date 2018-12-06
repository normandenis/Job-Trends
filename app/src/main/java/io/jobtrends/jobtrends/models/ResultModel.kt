package io.jobtrends.jobtrends.models

import android.databinding.ObservableArrayList
import com.google.gson.annotations.SerializedName

/**
 * @param id
 * @param index
 * @param score
 * @param resultSourceModel
 * @param type
 */
data class ResultModel(

    @SerializedName("near")
    var nears: ObservableArrayList<AlternativeModel> = ObservableArrayList(),

    @SerializedName("possible")
    var possibles: ObservableArrayList<AlternativeModel> = ObservableArrayList(),

    @SerializedName("formations")
    var formations: ObservableArrayList<AlternativeModel> = ObservableArrayList()

) : Model
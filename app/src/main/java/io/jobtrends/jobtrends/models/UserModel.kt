package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableDoubleAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

/**
 * @param birthday
 * @param city
 * @param country
 * @param firstName
 * @param job
 * @param lastName
 * @param postalCode
 */
data class UserModel(

    @SerializedName("birthday")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var birthday: ObservableField<Double> = ObservableField(0.0),

    @SerializedName("city")
    @JsonAdapter(ObservableStringAdapter::class)
    var city: ObservableField<String> = ObservableField(""),

    @SerializedName("country")
    @JsonAdapter(ObservableStringAdapter::class)
    var country: ObservableField<String> = ObservableField(""),

    @SerializedName("firstName")
    @JsonAdapter(ObservableStringAdapter::class)
    var firstName: ObservableField<String> = ObservableField(""),

    @SerializedName("job")
    @JsonAdapter(ObservableStringAdapter::class)
    var job: ObservableField<String> = ObservableField(""),

    @SerializedName("lastName")
    @JsonAdapter(ObservableStringAdapter::class)
    var lastName: ObservableField<String> = ObservableField(""),

    @SerializedName("postalCode")
    @JsonAdapter(ObservableDoubleAdapter::class)
    var postalCode: ObservableField<Double> = ObservableField(0.0)
) : Model

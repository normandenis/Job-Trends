package io.jobtrends.jobtrends.models

import android.databinding.ObservableField
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import io.jobtrends.jobtrends.adapters.ObservableLongAdapter
import io.jobtrends.jobtrends.adapters.ObservableStringAdapter

data class UserModel(

    @SerializedName("birthday")
    @JsonAdapter(ObservableLongAdapter::class)
    var birthday: ObservableField<Long> = ObservableField(0),

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
    @JsonAdapter(ObservableLongAdapter::class)
    var postalCode: ObservableField<Long> = ObservableField(0)
) : Model

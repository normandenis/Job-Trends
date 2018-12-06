package io.jobtrends.jobtrends.adapters

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.ObservableField
import android.support.v7.widget.AppCompatRatingBar
import android.widget.TextView
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.DecimalFormat


@BindingAdapter("android:rating")
fun setText(appCompatRatingBar: AppCompatRatingBar, value: Double) {
    try {
        appCompatRatingBar.rating = if (value > 0.0) {
            value.toFloat()
        } else {
            0.0.toFloat()
        }
    } catch (exception: Exception) {
        appCompatRatingBar.rating = 0.0.toFloat()
    }
}

@InverseBindingAdapter(attribute = "android:rating")
fun getText(appCompatRatingBar: AppCompatRatingBar): Double {
    return try {
        appCompatRatingBar.rating.toDouble()
    } catch (exception: Exception) {
        0.0
    }
}

@BindingAdapter("android:text")
fun setText(view: TextView, value: Double) {
    view.text = try {
        val format = DecimalFormat("0.##")
        format.format(value)
    } catch (exception: Exception) {
        ""
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: TextView): Double {
    return try {
        view.text.toString().toDouble()
    } catch (exception: Exception) {
        0.0
    }
}

class ObservableStringAdapter : TypeAdapter<ObservableField<String>>() {

    override fun write(json: JsonWriter, value: ObservableField<String>) {
        json.value(value.get())
    }

    override fun read(json: JsonReader): ObservableField<String> {
        return ObservableField(json.nextString())
    }
}

class ObservableDoubleToStringAdapter : TypeAdapter<ObservableField<Double>>() {
    override fun write(jsonWriter: JsonWriter, value: ObservableField<Double>) {
        try {
            jsonWriter.value(value.get().toString())
        } catch (exception: Exception) {
            jsonWriter.value("")
        }
    }

    override fun read(jsonReader: JsonReader): ObservableField<Double> {
        return try {
            ObservableField(jsonReader.nextString().toDouble())
        } catch (exception: Exception) {
            ObservableField(0.0)
        }
    }

}

class ObservableDoubleAdapter : TypeAdapter<ObservableField<Double>>() {

    override fun write(jsonWriter: JsonWriter, value: ObservableField<Double>) {
        jsonWriter.value(value.get())
    }

    override fun read(jsonReader: JsonReader): ObservableField<Double> {
        return ObservableField(jsonReader.nextDouble())
    }
}

class ObservableBooleanAdapter : TypeAdapter<ObservableField<Boolean>>() {

    override fun write(json: JsonWriter, value: ObservableField<Boolean>) {
        json.value(value.get())
    }

    override fun read(json: JsonReader): ObservableField<Boolean> {
        return ObservableField(json.nextBoolean())
    }
}
package io.jobtrends.jobtrends.adapters

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.ObservableField
import android.support.v7.widget.AppCompatRatingBar
import android.widget.TextView
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


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
fun setText(view: TextView, value: Long) {
    try {
        view.text = if (value > 0) {
            value.toString()
        } else {
            ""
        }
    } catch (exception: Exception) {
        view.text = ""
    }
}

@InverseBindingAdapter(attribute = "android:text")
fun getText(view: TextView): Long {
    return try {
        view.text.toString().toLong()
    } catch (exception: Exception) {
        0
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

class ObservableLongAdapter : TypeAdapter<ObservableField<Long>>() {

    override fun write(json: JsonWriter, value: ObservableField<Long>) {
        json.value(value.get())
    }

    override fun read(json: JsonReader): ObservableField<Long> {
        return ObservableField(json.nextLong())
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
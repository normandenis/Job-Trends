package io.jobtrends.jobtrends.adapters

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.ObservableField
import android.widget.TextView
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


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
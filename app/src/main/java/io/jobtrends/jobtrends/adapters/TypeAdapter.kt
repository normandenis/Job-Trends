package io.jobtrends.jobtrends.adapters

import android.databinding.ObservableField
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

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
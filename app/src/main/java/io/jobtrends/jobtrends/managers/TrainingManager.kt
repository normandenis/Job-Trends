package io.jobtrends.jobtrends.managers

import android.content.Context
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.TrainingModel
import javax.inject.Inject

class TrainingManager : RecyclerManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var context: Context

    private val trainingModelModelArray: Array<TrainingModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_training)
        trainingModelModelArray = jsonManager.deserialize(data)
    }

    override fun getItem(index: Int): Any {
        return trainingModelModelArray[index]
    }

    override fun getCount(): Int {
        return trainingModelModelArray.size
    }


}
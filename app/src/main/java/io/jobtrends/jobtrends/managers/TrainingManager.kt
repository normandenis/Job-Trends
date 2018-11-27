package io.jobtrends.jobtrends.managers

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.models.TrainingModel
import javax.inject.Inject

class TrainingManager : RecyclerManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var context: Context

    private val trainings: MutableList<TrainingModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_training)
        trainings = mutableListOf(jsonManager.deserialize(data))
    }

    override fun getItem(index: Int): Any {
        return trainings[index]
    }

    override fun getCount(): Int {
        return trainings.size
    }

    fun addModel(training: TrainingModel) {
        trainings.add(training)
    }

    fun startDialog() {
        val dialog = Dialog(context, R.style.JobTrends_Theme_Dailog_Alert)
        val inflater = LayoutInflater.from(context)
        val viewGroup: ViewGroup = (context as Activity).findViewById(android.R.id.content)
        val binding: DialogTrainingBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_training, viewGroup, false)
        dialog.setContentView(binding.root)
        dialog.show()

    }

    fun removeModel(training: TrainingModel? = null, index: Int? = null): Boolean {
        when {
            training != null -> trainings.remove(training)
            index != null -> trainings.removeAt(index)
            else -> return false
        }
        return true
    }
}
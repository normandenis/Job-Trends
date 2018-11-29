package io.jobtrends.jobtrends.managers

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityListener
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.models.TrainingModel

class TrainingManager : IManager {

    private val trainings: MutableList<TrainingModel>

    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogTrainingBinding

    private var activityListener: ActivityListener? = null

    init {
        App.component.inject(this)
        trainings = mutableListOf()
    }

    override fun getItem(index: Int): Any {
        return trainings[index]
    }

    override fun getCount(): Int {
        return trainings.size
    }

    override fun registerActivityListener(activityListener: ActivityListener) {
        this.activityListener = activityListener
    }

    override fun unregisterActivityListener() {
        activityListener = null
    }

    fun addModel(training: TrainingModel) {
        dialog?.dismiss()
        trainings.add(training)
        activityListener?.onNavNext()
    }

    fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityListener as Context, R.style.JobTrends_Theme_Dailog_Alert)
            inflater = LayoutInflater.from(activityListener as Context)
            viewGroup = (activityListener as Activity).findViewById(android.R.id.content)
            binding = DataBindingUtil.inflate(inflater, R.layout.dialog_training, viewGroup, false)
            binding.trainingManager = this
            dialog?.setContentView(binding.root)
        }
        val training = TrainingModel()
        binding.trainingModel = training
        dialog?.show()
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
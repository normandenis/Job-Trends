package io.jobtrends.jobtrends.viewmodels

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.*
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.models.IModel
import io.jobtrends.jobtrends.models.TrainingModel

class TrainingViewModel : CurriculumViewModel {
    private val trainings: MutableList<TrainingModel>
    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogTrainingBinding
    private var activityManager: ActivityManager? = null

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

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun addModel(model: IModel) {
        dialog?.dismiss()
        trainings.add(model as TrainingModel)
        activityManager?.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityManager as Context, R.style.JobTrends_Theme_Dailog_Alert)
            inflater = LayoutInflater.from(activityManager as Context)
            viewGroup = (activityManager as Activity).findViewById(android.R.id.content)
            binding = DataBindingUtil.inflate(inflater, R.layout.dialog_training, viewGroup, false)
            binding.trainingManager = this
            dialog?.setContentView(binding.root)
        }
        val training = TrainingModel()
        binding.trainingModel = training
        dialog?.show()
    }

    override fun onNextStep() {
        activityManager?.setState(EXPERIENCE_STATE)
        activityManager?.build()
    }

    override fun removeModel(model: IModel) {
        trainings.remove(model)
        activityManager?.build()
    }
}

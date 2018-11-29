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
import io.jobtrends.jobtrends.databinding.DialogExperienceBinding
import io.jobtrends.jobtrends.models.ExperienceModel
import io.jobtrends.jobtrends.models.IModel

class ExperienceViewModel : CurriculumViewModel {
    private val experiences: MutableList<ExperienceModel>
    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogExperienceBinding
    private var activityManager: ActivityManager? = null

    init {
        App.component.inject(this)
        experiences = mutableListOf()
    }

    override fun getItem(index: Int): Any {
        return experiences[index]
    }

    override fun getCount(): Int {
        return experiences.size
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun addModel(model: IModel) {
        dialog?.dismiss()
        experiences.add(model as ExperienceModel)
        activityManager?.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityManager as Context, R.style.JobTrends_Theme_Dailog_Alert)
            inflater = LayoutInflater.from(activityManager as Context)
            viewGroup = (activityManager as Activity).findViewById(android.R.id.content)
            binding =
                    DataBindingUtil.inflate(inflater, R.layout.dialog_experience, viewGroup, false)
            binding.experienceViewModel = this
            dialog?.setContentView(binding.root)
        }
        val experienceModel = ExperienceModel()
        binding.experienceModel = experienceModel
        dialog?.show()
    }

    override fun onNextStep() {
        activityManager?.setState(PASSION_STATE)
        activityManager?.build()
    }

    override fun removeModel(model: IModel) {
        experiences.remove(model)
        activityManager?.build()
    }
}

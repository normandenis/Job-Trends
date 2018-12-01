package io.jobtrends.jobtrends.viewmodels

import android.R.id.content
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.dialog_training
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.EXPERIENCE_STATE
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.TrainingModel
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel.TrainingListKey.TRAINING_LIST_KEY

class TrainingViewModel : CurriculumViewModel {

    enum class TrainingListKey : ListKey {
        TRAINING_LIST_KEY
    }

    override var container: MutableMap<ListKey, MutableList<Model>> = mutableMapOf()
    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogTrainingBinding
    private var activityManager: ActivityManager? = null

    init {
        App.component.inject(this)
        container[TRAINING_LIST_KEY] = mutableListOf()
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return container[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return container[key]!!.size
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun addModel(model: Model) {
        dialog?.dismiss()
        container[TRAINING_LIST_KEY]!!.add(model)
        activityManager?.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityManager as Context, JobTrends_Theme_Dailog_Alert)
            inflater = from(activityManager as Context)
            viewGroup = (activityManager as Activity).findViewById(content)
            binding = inflate(inflater, dialog_training, viewGroup, false)
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

    override fun removeModel(model: Model) {
        container[TRAINING_LIST_KEY]!!.remove(model)
        activityManager?.build()
    }
}

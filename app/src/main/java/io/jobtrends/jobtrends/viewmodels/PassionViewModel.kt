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
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogPassionBinding
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.models.IModel
import io.jobtrends.jobtrends.models.PassionModel

class PassionViewModel : CurriculumViewModel {
    private val passions: MutableList<PassionModel>
    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogPassionBinding
    private var activityManager: ActivityManager? = null

    init {
        App.component.inject(this)
        passions = mutableListOf()
    }

    override fun getItem(index: Int): Any {
        return passions[index]
    }

    override fun getCount(): Int {
        return passions.size
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun addModel(model: IModel) {
        dialog?.dismiss()
        passions.add(model as PassionModel)
        activityManager?.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityManager as Context, R.style.JobTrends_Theme_Dailog_Alert)
            inflater = LayoutInflater.from(activityManager as Context)
            viewGroup = (activityManager as Activity).findViewById(android.R.id.content)
            binding = DataBindingUtil.inflate(inflater, R.layout.dialog_passion, viewGroup, false)
            binding.passionViewModel = this
            dialog?.setContentView(binding.root)
        }
        val passionModel = PassionModel()
        binding.passionModel = passionModel
        dialog?.show()
    }

    override fun onNextStep() {
    }

    override fun removeModel(model: IModel) {
        passions.remove(model)
        activityManager?.build()
    }
}

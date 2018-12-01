package io.jobtrends.jobtrends.viewmodels

import android.R.id.content
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.dialog_passion
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogPassionBinding
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.PassionModel
import io.jobtrends.jobtrends.viewmodels.PassionViewModel.PassionListKey.PASSION_LIST_KEY

class PassionViewModel : CurriculumViewModel {

    enum class PassionListKey : ListKey {
        PASSION_LIST_KEY
    }

    private var dialog: Dialog? = null
    private var activityManager: ActivityManager? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogPassionBinding
    override var container: MutableMap<ListKey, MutableList<Model>> = mutableMapOf()

    init {
        App.component.inject(this)
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
        container[PASSION_LIST_KEY]!!.add(model as PassionModel)
        activityManager?.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activityManager as Context, JobTrends_Theme_Dailog_Alert)
            inflater = from(activityManager as Context)
            viewGroup = (activityManager as Activity).findViewById(content)
            binding = inflate(inflater, dialog_passion, viewGroup, false)
            binding.passionViewModel = this
            dialog?.setContentView(binding.root)
        }
        val passionModel = PassionModel()
        binding.passionModel = passionModel
        dialog?.show()
    }

    override fun onNextStep() {
    }

    override fun removeModel(model: Model) {
        container[PASSION_LIST_KEY]!!.remove(model)
        activityManager?.build()
    }
}

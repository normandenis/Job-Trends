package io.jobtrends.jobtrends.viewmodels

import android.R.id.content
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.dialog_passion
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogPassionBinding
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.PassionModel
import io.jobtrends.jobtrends.viewmodels.PassionViewModel.PassionListKey.PASSION_LIST_KEY

@Suppress("UNCHECKED_CAST")
class PassionViewModel : CurriculumViewModel {

    enum class PassionListKey : ListKey {
        PASSION_LIST_KEY
    }

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogPassionBinding

    init {
        App.component.inject(this)
        lists[PASSION_LIST_KEY] = ObservableArrayList<PassionModel>() as ObservableList<Model>
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]?.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun addModel(model: Model) {
        dialog?.dismiss()
        lists[PASSION_LIST_KEY]!!.add(model as PassionModel)
        activity.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activity as Context, JobTrends_Theme_Dailog_Alert)
            inflater = from(activity as Context)
            viewGroup = (activity as Activity).findViewById(content)
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
        lists[PASSION_LIST_KEY]!!.remove(model)
        activity.build()
    }
}

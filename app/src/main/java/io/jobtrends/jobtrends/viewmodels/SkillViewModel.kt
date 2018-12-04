package io.jobtrends.jobtrends.viewmodels

import android.R.id.content
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.AppCompatRatingBar
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import android.widget.RatingBar
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.R.layout.dialog_skill
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogSkillBinding
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.SkillModel
import io.jobtrends.jobtrends.viewmodels.SkillViewModel.SkillListKey.SKILL_LIST_KEY

@Suppress("UNCHECKED_CAST")
class SkillViewModel : CurriculumViewModel {

    enum class SkillListKey : ListKey {
        SKILL_LIST_KEY
    }

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    private var dialog: Dialog? = null
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogSkillBinding

    init {
        App.component.inject(this)
        lists[SKILL_LIST_KEY] = ObservableArrayList<SkillModel>() as ObservableList<Model>
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
        lists[SKILL_LIST_KEY]!!.add(model)
        activity.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activity as Context, JobTrends_Theme_Dailog_Alert)
            inflater = from(activity as Context)
            viewGroup = (activity as Activity).findViewById(content)
            binding = inflate(inflater, dialog_skill, viewGroup, false)
            binding.skillViewModel = this
            dialog?.setContentView(binding.root)
        }
        val passionModel = SkillModel()
        binding.skillModel = passionModel
        dialog?.show()
    }

    override fun onNextStep() {
    }

    override fun removeModel(model: Model) {
        lists[SKILL_LIST_KEY]!!.remove(model)
        activity.build()
    }
}

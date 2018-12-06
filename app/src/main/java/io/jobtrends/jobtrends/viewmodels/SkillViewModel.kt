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
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import com.orhanobut.logger.Logger.d
import com.orhanobut.logger.Logger.json
import io.jobtrends.jobtrends.R.layout.dialog_skill
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity
import io.jobtrends.jobtrends.activities.CurriculumActivity.CurriculumState.*
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogSkillBinding
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AnalysisModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.SkillModel
import io.jobtrends.jobtrends.viewmodels.SkillViewModel.SkillListKey.SKILL_LIST_KEY
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SkillViewModel : CurriculumViewModel {

    enum class SkillListKey : ListKey {
        SKILL_LIST_KEY
    }

    companion object {
        private const val SKILL_URL = "/skill"
        private const val RESULT_URL = "results/"
    }

    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override lateinit var activity: ActivityManager

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var resultViewModel: ResultViewModel

    private lateinit var analysisModel: AnalysisModel
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
        var json: String
        for (skillModel in lists[SKILL_LIST_KEY] as ObservableList<SkillModel>) {
            json = jsonManager.serialize(skillModel)
            apiManager.request(
                POST,
                analysisModel.id.get() + SKILL_URL,
                ::addSkillCallback,
                json
            )
        }
        apiManager.request(POST, analysisModel.id.get()!!, ::closeAnalysisCallback)
    }

    override fun removeModel(model: Model) {
        lists[SKILL_LIST_KEY]!!.remove(model)
        activity.build()
    }

    private fun addSkillCallback(statusCode: Int, data: String) {
        d(statusCode)
        json(data)
        if (statusCode != 200) {
            return
        }
    }

    private fun closeAnalysisCallback(statusCode: Int, data: String) {
        d(statusCode)
        json(data)
        if (statusCode != 200) {
            return
        }
        apiManager.request(GET, RESULT_URL + analysisModel.id.get(), { statusCode1, data1 ->
            resultViewModel.resultCallback(statusCode1, data1)
        })
        activity.setState(RESULT_STATE)
        activity.build()
    }

    fun startAnalysisCallback(statusCode: Int, data: String) {
        if (statusCode != 200) {
            return
        }
        analysisModel = jsonManager.deserialize(data)
    }
}

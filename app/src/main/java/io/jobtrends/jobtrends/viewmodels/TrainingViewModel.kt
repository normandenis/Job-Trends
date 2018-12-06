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
import com.android.volley.Request.Method.POST
import io.jobtrends.jobtrends.R.layout.dialog_training
import io.jobtrends.jobtrends.R.style.JobTrends_Theme_Dailog_Alert
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.CurriculumActivity.TrainingActivityState.EXPERIENCE_STATE
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogTrainingBinding
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.models.AnalaysisModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.models.TrainingModel
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel.TrainingListKey.TRAINING_LIST_KEY
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TrainingViewModel : CurriculumViewModel {

    enum class TrainingListKey : ListKey {
        TRAINING_LIST_KEY
    }

    companion object {
        private const val TRAINING_URL = "/education"
    }

    override lateinit var activity: ActivityManager
    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()

    @Inject
    lateinit var jsonManager: JsonManager
    @Inject
    lateinit var apiManager: ApiManager

    private var dialog: Dialog? = null
    private lateinit var analaysisModel: AnalaysisModel
    private lateinit var inflater: LayoutInflater
    private lateinit var viewGroup: ViewGroup
    private lateinit var binding: DialogTrainingBinding

    init {
        App.component.inject(this)
        lists[TRAINING_LIST_KEY] = ObservableArrayList<TrainingModel>() as ObservableList<Model>
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]!!.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun addModel(model: Model) {
        dialog!!.dismiss()
        lists[TRAINING_LIST_KEY]!!.add(model)
        activity.build()
    }

    override fun startDialog() {
        if (dialog == null) {
            dialog = Dialog(activity as Context, JobTrends_Theme_Dailog_Alert)
            inflater = from(activity as Context)
            viewGroup = (activity as Activity).findViewById(content)
            binding = inflate(inflater, dialog_training, viewGroup, false)
            binding.trainingManager = this
            dialog!!.setContentView(binding.root)
        }
        val training = TrainingModel()
        binding.trainingModel = training
        dialog!!.show()
    }

    override fun onNextStep() {
        var json: String
        for (trainingModel in lists[TRAINING_LIST_KEY] as ObservableList<TrainingModel>) {
            json = jsonManager.serialize(trainingModel)
            apiManager.request(
                POST,
                analaysisModel.id.get() + TRAINING_URL,
                ::addTrainingCallback,
                json
            )
        }
        activity.setState(EXPERIENCE_STATE)
        activity.build()
    }

    override fun removeModel(model: Model) {
        lists[TRAINING_LIST_KEY]!!.remove(model)
        activity.build()
    }

    private fun addTrainingCallback(statusCode: Int, data: String) {}

    fun startAnalysisCallback(statusCode: Int, data: String) {
        analaysisModel = jsonManager.deserialize(data)
    }
}

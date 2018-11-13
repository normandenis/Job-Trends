package io.jobtrends.jobtrends.managers

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.DialogFormationBinding
import io.jobtrends.jobtrends.models.JobStatisticModel
import javax.inject.Inject

class JobManager : RecyclerManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    @Inject
    lateinit var context: Context

    private val jobStatisticModelArray: Array<JobStatisticModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_job)
        jobStatisticModelArray = jsonManager.deserialize(data)
    }

    override fun getItem(index: Int): Any {
        return jobStatisticModelArray[index]
    }

    override fun getCount(): Int {
        return jobStatisticModelArray.size
    }

    fun onClick(context: Context) {

        val dialog = Dialog(context)
        val inflater = LayoutInflater.from(context)
        val viewGroup: ViewGroup = (context as Activity).findViewById(android.R.id.content)
        val binding: DialogFormationBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_formation, viewGroup, false)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        dialog.setContentView(binding.root)
        dialog.show()
        dialog.window?.attributes = lp
    }
}
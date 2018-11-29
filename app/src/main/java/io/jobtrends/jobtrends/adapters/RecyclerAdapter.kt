package io.jobtrends.jobtrends.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.databinding.SurfaceHomeBinding
import io.jobtrends.jobtrends.databinding.SurfaceJobBinding
import io.jobtrends.jobtrends.databinding.SurfaceTrainingBinding
import io.jobtrends.jobtrends.viewmodels.HomeViewModel
import io.jobtrends.jobtrends.viewmodels.ViewModel
import io.jobtrends.jobtrends.viewmodels.JobViewModel
import io.jobtrends.jobtrends.viewmodels.TrainingViewModel
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.JobStatisticModel
import io.jobtrends.jobtrends.models.TrainingModel

class RecyclerAdapter(private val viewModel: ViewModel,
                      private val layoutId: Int) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(group: ViewGroup, itemViewType: Int): ViewHolder {
        val height: Int
        val width: Int
        val inflater = LayoutInflater.from(group.context)
        val binding: Any = when (layoutId) {
            R.layout.surface_home -> {
                height = group.measuredHeight
                width = height
                DataBindingUtil.inflate(inflater, layoutId, null, false)
            }
            R.layout.surface_training -> {
                height = WRAP_CONTENT
                width = MATCH_PARENT
                DataBindingUtil.inflate(inflater, layoutId, null, false)
            }
            else -> {
                height = MATCH_PARENT
                width = WRAP_CONTENT
                DataBindingUtil.inflate(inflater, layoutId, null, false)
            }
        }
        val params = ViewGroup.LayoutParams(width, height)
        (binding as ViewDataBinding).root.layoutParams = params
        return HolderAdapter(binding)
    }

    override fun getItemCount(): Int = viewModel.getCount()

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        when (layoutId) {
            R.layout.surface_home -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceHomeBinding>)
                tmpHolder.binding.homeViewModel = viewModel as HomeViewModel
                tmpHolder.binding.jobModel = viewModel.getItem(index) as JobModel
            }
            R.layout.surface_training -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceTrainingBinding>)
                tmpHolder.binding.trainingManager = viewModel as TrainingViewModel
                tmpHolder.binding.trainingModel = viewModel.getItem(index) as TrainingModel
            }
            else -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceJobBinding>)
                tmpHolder.binding.jobViewModel = viewModel as JobViewModel
                tmpHolder.binding.jobStatisticModel = viewModel.getItem(index) as JobStatisticModel
            }
        }
    }

    class HolderAdapter<T>(val binding: T) : ViewHolder((binding as ViewDataBinding).root)
}
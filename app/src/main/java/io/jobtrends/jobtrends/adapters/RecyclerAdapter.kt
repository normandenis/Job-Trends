package io.jobtrends.jobtrends.adapters

import android.databinding.DataBindingUtil.inflate
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.*
import io.jobtrends.jobtrends.databinding.*
import io.jobtrends.jobtrends.models.*
import io.jobtrends.jobtrends.viewmodels.*

@Suppress("UNCHECKED_CAST")
class RecyclerAdapter(
    private val viewModel: ViewModel,
    private val layoutId: Int,
    private val key: ListKey
) : Adapter<ViewHolder>(), AdapterManager {

    init {
        viewModel.registerAdapterManager(key, ListChangedAdapter(this))
    }

    override fun onCreateViewHolder(group: ViewGroup, itemViewType: Int): ViewHolder {
        val inflater = from(group.context)
        val binding: Any = inflate(inflater, layoutId, null, false)
        return HolderAdapter(binding)
    }

    override fun getItemCount(): Int = viewModel.getCount(key)

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        when (layoutId) {
            surface_home -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceHomeBinding>)
                tmpHolder.binding.homeViewModel = viewModel as HomeViewModel
                tmpHolder.binding.jobModel = viewModel.getItem(key, index) as JobModel
            }
            surface_job -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceJobBinding>)
                tmpHolder.binding.statisticModel = viewModel.getItem(key, index) as StatisticModel
            }
            surface_training -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceTrainingBinding>)
                tmpHolder.binding.trainingViewModel = viewModel as TrainingViewModel
                tmpHolder.binding.trainingModel = viewModel.getItem(key, index) as TrainingModel
            }
            surface_experience -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceExperienceBinding>)
                tmpHolder.binding.experienceViewModel = viewModel as ExperienceViewModel
                tmpHolder.binding.experienceModel = viewModel.getItem(key, index) as ExperienceModel
            }
            surface_skill -> {
                val tmpHolder = (holder as HolderAdapter<SurfaceSkillBinding>)
                tmpHolder.binding.skillViewModel = viewModel as SkillViewModel
                tmpHolder.binding.skillModel = viewModel.getItem(key, index) as SkillModel
            }
        }
    }

    class HolderAdapter<T>(val binding: T) : ViewHolder((binding as ViewDataBinding).root)
}
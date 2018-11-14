package io.jobtrends.jobtrends.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentHomeBinding
import io.jobtrends.jobtrends.databinding.FragmentJobBinding
import io.jobtrends.jobtrends.managers.HomeManager
import io.jobtrends.jobtrends.managers.JobManager
import io.jobtrends.jobtrends.managers.RecyclerManager
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.models.JobStatisticModel

class RecyclerAdapter(val context: Context,
                      val recyclerManager: RecyclerManager,
                      val layoutId: Int) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(group: ViewGroup, itemViewType: Int): ViewHolder {
        val height: Int
        val width: Int
        val inflater = LayoutInflater.from(group.context)
        val binding: Any = when (layoutId) {
            R.layout.fragment_home -> {
                width = group.measuredWidth / 2
                height = width
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false)
            }
            else -> {
                width = (group.measuredWidth * 0.7).toInt()
                height = (group.measuredHeight * 0.6).toInt()
                DataBindingUtil.inflate(inflater, R.layout.fragment_job, null, false)
            }
        }
        val params = ViewGroup.LayoutParams(width, height)
        (binding as ViewDataBinding).root.layoutParams = params
        return HolderAdapter(binding)
    }

    override fun getItemCount(): Int = recyclerManager.getCount()

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        when (layoutId) {
            R.layout.fragment_home -> {
                val tmpHolder = (holder as HolderAdapter<FragmentHomeBinding>)
                tmpHolder.binding.homeManager = recyclerManager as HomeManager
                tmpHolder.binding.jobModel = recyclerManager.getItem(index) as JobModel
                tmpHolder.binding.context = context
            }
            else -> {
                val tmpHolder = (holder as HolderAdapter<FragmentJobBinding>)
                tmpHolder.binding.jobManager = recyclerManager as JobManager
                tmpHolder.binding.jobStatisticModel = recyclerManager.getItem(index) as JobStatisticModel
            }
        }
    }

    class HolderAdapter<T>(val binding: T) : ViewHolder((binding as ViewDataBinding).root)
}
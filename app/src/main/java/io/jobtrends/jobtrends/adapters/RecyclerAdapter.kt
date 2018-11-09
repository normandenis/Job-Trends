package io.jobtrends.jobtrends.adapters

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Point
import android.support.v7.widget.RecyclerView
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
import io.jobtrends.jobtrends.models.JobStatisticModel
import javax.inject.Inject

class RecyclerAdapter(val recyclerManager: RecyclerManager,
                      val layoutId: Int) : Adapter<ViewHolder>() {

    @Inject
    lateinit var homeManager: HomeManager

    @Inject
    lateinit var jobManager: JobManager

    private var size: Int = 0

    init {
        App.component.inject(this)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val context = recyclerView.context as Activity
        val windowDimensions = Point()
        context.windowManager.defaultDisplay.getSize(windowDimensions)
        size = Math.round(windowDimensions.y * 0.25f)
    }

    override fun onCreateViewHolder(group: ViewGroup, itemViewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(group.context)
        val binding: Any = when (layoutId) {
            R.layout.fragment_home -> {
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false)
            }
            else -> {
                DataBindingUtil.inflate(inflater, R.layout.fragment_job, null, false)
            }
        }
        val params = ViewGroup.LayoutParams(size, size)
        (binding as ViewDataBinding).root.layoutParams = params
        return HolderAdapter(binding)
    }

    override fun getItemCount(): Int {
        return when (layoutId) {
            R.layout.fragment_home -> homeManager.getCount()
            else -> jobManager.getCount()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        when (layoutId) {
            R.layout.fragment_home -> {
                val tmpHolder = (holder as HolderAdapter<FragmentHomeBinding>)
                tmpHolder.binding.homeManager = homeManager
                tmpHolder.binding.jobModel = homeManager.getItem(index)
            }
            R.layout.fragment_job -> {
                val tmpHolder = (holder as HolderAdapter<FragmentJobBinding>)
                tmpHolder.binding.jobManager = recyclerManager as JobManager
                tmpHolder.binding.jobStatisticModel = recyclerManager.getItem(index) as JobStatisticModel
            }
        }
    }

    class HolderAdapter<T>(val binding: T) : ViewHolder((binding as ViewDataBinding).root)
}
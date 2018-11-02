package io.jobtrends.jobtrends.adapters

import android.app.Activity
import android.databinding.DataBindingUtil
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.HomeAdapter.HomeHolder
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentHomeBinding
import io.jobtrends.jobtrends.managers.HomeManager
import javax.inject.Inject


class HomeAdapter : Adapter<HomeHolder>() {

    @Inject
    lateinit var homeManager: HomeManager

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

    override fun onCreateViewHolder(group: ViewGroup, itemViewType: Int): HomeHolder {
        val inflater = LayoutInflater.from(group.context)
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false)
        val params = ViewGroup.LayoutParams(size, size)
        binding.root.layoutParams = params
        return HomeHolder(binding)
    }

    override fun getItemCount(): Int = homeManager.getCount()

    override fun onBindViewHolder(holder: HomeHolder, index: Int) {
        holder.binding.homeManager = homeManager
        holder.binding.jobModel = homeManager.getItem(index)
        holder.binding.executePendingBindings()
    }

    class HomeHolder(val binding: FragmentHomeBinding) : ViewHolder(binding.root)
}
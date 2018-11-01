package io.jobtrends.jobtrends.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentBoardingBinding
import io.jobtrends.jobtrends.models.BoardingModel

class BoardingFragment : Fragment() {

    lateinit var boardingModel: BoardingModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentBoardingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_boarding, container, false)
        binding.boardingFragment = this
        binding.boardingModel = boardingModel
        return binding.root
    }
}

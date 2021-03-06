package io.jobtrends.jobtrends.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentCurriculumEmptyBinding
import io.jobtrends.jobtrends.viewmodels.ExperienceViewModel
import javax.inject.Inject

class ExperienceEmptyFragment : Fragment() {

    @Inject
    lateinit var experienceViewModel: ExperienceViewModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCurriculumEmptyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_curriculum_empty, container, false)
        binding.curriculumManager = experienceViewModel
        return binding.root
    }
}

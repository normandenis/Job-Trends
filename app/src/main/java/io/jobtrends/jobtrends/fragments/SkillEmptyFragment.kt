package io.jobtrends.jobtrends.fragments

import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.fragment_curriculum_empty
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentCurriculumEmptyBinding
import io.jobtrends.jobtrends.viewmodels.SkillViewModel
import javax.inject.Inject

class SkillEmptyFragment : Fragment() {

    @Inject
    lateinit var skillViewModel: SkillViewModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCurriculumEmptyBinding =
            inflate(inflater, fragment_curriculum_empty, container, false)
        binding.curriculumManager = skillViewModel
        return binding.root
    }
}

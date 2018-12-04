package io.jobtrends.jobtrends.fragments


import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.fragment_curriculum
import io.jobtrends.jobtrends.R.layout.surface_skill
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App.Companion.component
import io.jobtrends.jobtrends.databinding.FragmentCurriculumBinding
import io.jobtrends.jobtrends.viewmodels.SkillViewModel
import io.jobtrends.jobtrends.viewmodels.SkillViewModel.SkillListKey.SKILL_LIST_KEY
import kotlinx.android.synthetic.main.fragment_curriculum.*
import javax.inject.Inject


class SkillFragment : Fragment() {

    @Inject
    lateinit var skillViewModel: SkillViewModel

    init {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCurriculumBinding =
            inflate(inflater, fragment_curriculum, container, false)
        binding.curriculumManager = skillViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_0.adapter = RecyclerAdapter(skillViewModel, surface_skill, SKILL_LIST_KEY)
        super.onViewCreated(view, savedInstanceState)
    }
}

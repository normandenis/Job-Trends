package io.jobtrends.jobtrends.fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentCurriculumBinding
import io.jobtrends.jobtrends.viewmodels.PassionViewModel
import kotlinx.android.synthetic.main.fragment_curriculum.*
import javax.inject.Inject


class PassionFragment : Fragment() {

    @Inject
    lateinit var passionViewModel: PassionViewModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCurriculumBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_curriculum, container, false)
        binding.curriculumManager = passionViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_0.adapter = RecyclerAdapter(passionViewModel, R.layout.surface_training)
        super.onViewCreated(view, savedInstanceState)
    }
}

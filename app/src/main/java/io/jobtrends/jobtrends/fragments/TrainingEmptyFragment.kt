package io.jobtrends.jobtrends.fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentTrainingEmptyBinding
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Inject


class TrainingEmptyFragment : Fragment() {

    @Inject
    lateinit var wrapper: Wrapper

    init {
        App.component.inject(this)
        wrapper.register(context, true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTrainingEmptyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_training_empty, container, false)
        return binding.root
    }
}

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
import io.jobtrends.jobtrends.databinding.FragmentTrainingBinding
import io.jobtrends.jobtrends.managers.TrainingManager
import kotlinx.android.synthetic.main.fragment_training.*
import javax.inject.Inject


class TrainingFragment : Fragment() {

    @Inject
    lateinit var trainingManager: TrainingManager

    init {
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTrainingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_training, container, false)
        binding.trainingManager = trainingManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_0.adapter = RecyclerAdapter(trainingManager, R.layout.surface_training)
        super.onViewCreated(view, savedInstanceState)
    }
}

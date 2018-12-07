package io.jobtrends.jobtrends.fragments

import android.content.Context
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jobtrends.jobtrends.R.layout.fragment_user
import io.jobtrends.jobtrends.adapters.AutoCompleteAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.FragmentUserBinding
import io.jobtrends.jobtrends.viewmodels.UserViewModel
import io.jobtrends.jobtrends.viewmodels.UserViewModel.UserListKey.SEARCH_JOBS_LIST_KEY
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

class UserFragment : Fragment() {

    @Inject
    lateinit var userViewModel: UserViewModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserBinding = inflate(inflater, fragment_user, container, false)
        binding.userViewModel = userViewModel
        binding.userModel = userViewModel.userModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = AutoCompleteAdapter(activity as Context, userViewModel, SEARCH_JOBS_LIST_KEY)
        auto_complete_0.setAdapter(adapter)
        super.onViewCreated(view, savedInstanceState)
    }
}

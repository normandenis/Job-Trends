package io.jobtrends.jobtrends.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import io.jobtrends.jobtrends.fragments.BoardingFragment
import io.jobtrends.jobtrends.viewmodels.ViewModel
import io.jobtrends.jobtrends.models.BoardingModel

class PagerAdapter(fm: FragmentManager,
                   private val viewModel: ViewModel
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(index: Int): Fragment {
        val boardingFragment = BoardingFragment()
        boardingFragment.boardingModel = viewModel.getItem(index) as BoardingModel
        return boardingFragment
    }

    override fun getCount(): Int {
        return viewModel.getCount()
    }
}

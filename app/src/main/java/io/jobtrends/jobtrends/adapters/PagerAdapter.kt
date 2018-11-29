package io.jobtrends.jobtrends.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.fragments.BoardingFragment
import io.jobtrends.jobtrends.managers.BoardingManager
import javax.inject.Inject

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    @Inject
    lateinit var boardingManager: BoardingManager

    init {
        App.component.inject(this)
    }

    override fun getItem(index: Int): Fragment {
        val boardingFragment = BoardingFragment()
        boardingFragment.boardingModel = boardingManager.getItem(index)
        return boardingFragment
    }

    override fun getCount(): Int {
        return boardingManager.getCount()
    }
}

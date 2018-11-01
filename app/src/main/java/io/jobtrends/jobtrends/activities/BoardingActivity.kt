package io.jobtrends.jobtrends.activities

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.BoardingAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityBoardingBinding
import kotlinx.android.synthetic.main.activity_boarding.*

class BoardingActivity : AppCompatActivity(), OnPageChangeListener {

    private val boardingAdapter: BoardingAdapter
    val following: ObservableField<String>

    init {
        App.component.inject(this)
        boardingAdapter = BoardingAdapter(supportFragmentManager)
        following = ObservableField()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBoardingBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding)
        binding.boardingActivity = this
        pager_0.adapter = boardingAdapter
        pager_0.addOnPageChangeListener(this)
        indicator_0.setViewPager(pager_0)
        setFollowing()
    }

    override fun onPageScrollStateChanged(p0: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(p0: Int) {
        setFollowing()
    }

    private fun setFollowing() {
        if (pager_0.currentItem == boardingAdapter.count - 1) {
            following.set(getString(R.string.start))
        } else {
            following.set(getString(R.string.following))
        }
    }

    private fun setCurrentItem(nextIndex: Int) {
        val index = pager_0.currentItem + nextIndex
        if (index == boardingAdapter.count) {
            navTo()
        } else if (index >= 0 && index < boardingAdapter.count) {
            pager_0.currentItem = index
            setFollowing()
        }
    }

    fun navTo() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun onNavForward() {
        setCurrentItem(1)
    }
}

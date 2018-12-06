package io.jobtrends.jobtrends.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.HOME_STATE
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.NEXT_STATE
import io.jobtrends.jobtrends.adapters.PagerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityBoardingBinding
import io.jobtrends.jobtrends.viewmodels.BoardingViewModel
import io.jobtrends.jobtrends.viewmodels.BoardingViewModel.BoardingListKey.BOARDING_LIST_KEY
import kotlinx.android.synthetic.main.activity_boarding.*
import javax.inject.Inject


class BoardingActivity : AppCompatActivity(), PagerManager {

    enum class BoardingState : ActivityState {
        NEXT_STATE,
        HOME_STATE
    }

    @Inject
    lateinit var boardingViewModel: BoardingViewModel
    override var activityState: ActivityState = NEXT_STATE
    val following: ObservableField<String>

    init {
        App.component.inject(this)
        following = ObservableField()
        boardingViewModel.registerActivityManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBoardingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_boarding)
        binding.boardingViewModel = boardingViewModel
        binding.boardingActivity = this
        pager_0.adapter = PagerAdapter(supportFragmentManager, boardingViewModel, BOARDING_LIST_KEY)
        pager_0.addOnPageChangeListener(boardingViewModel)
        indicator_0.setViewPager(pager_0)
        setButtonText()
    }

    override fun setButtonText() {
        if (pager_0.currentItem == boardingViewModel.getCount(BOARDING_LIST_KEY) - 1) {
            following.set(getString(R.string.start))
        } else {
            following.set(getString(R.string.following))
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun build() {
        when (activityState) {
            NEXT_STATE -> {
                pager_0.currentItem++
                setButtonText()
            }
            HOME_STATE -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun getState(): ActivityState = activityState
}

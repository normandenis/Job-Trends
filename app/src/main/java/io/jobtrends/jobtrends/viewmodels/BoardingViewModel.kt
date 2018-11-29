package io.jobtrends.jobtrends.viewmodels

import android.support.v4.view.ViewPager.OnPageChangeListener
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.HOME_STATE
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.NEXT_STATE
import io.jobtrends.jobtrends.activities.PagerManager
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.BoardingModel
import javax.inject.Inject

class BoardingViewModel : ViewModel, OnPageChangeListener {
    @Inject
    lateinit var rawManager: RawManager
    @Inject
    lateinit var jsonManager: JsonManager
    private val boardingModelList: Array<BoardingModel>
    private var activityManager: PagerManager? = null
    private var currentItem: Int

    init {
        App.component.inject(this)
        currentItem = 0
        val data = rawManager.readRaw(R.raw.data_boarding)
        boardingModelList = jsonManager.deserialize(data)
    }

    override fun onPageScrollStateChanged(index: Int) {
        currentItem = index
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(index: Int) {
        currentItem = index
        activityManager?.setButtonText()
    }

    fun onClickSkip() {
        activityManager?.setState(HOME_STATE)
        activityManager?.build()
    }

    fun onClickNext() {
        val index = currentItem + 1
        if (index == getCount()) {
            activityManager?.setState(HOME_STATE)
        } else if (index >= 0 && index < getCount()) {
            activityManager?.setState(NEXT_STATE)
        }
        activityManager?.build()
    }

    override fun registerActivityManager(activityManager: ActivityManager) {
        this.activityManager = activityManager as PagerManager
    }

    override fun unregisterActivityManager() {
        activityManager = null
    }

    override fun getItem(index: Int): Any {
        return boardingModelList[index]
    }

    override fun getCount(): Int {
        return boardingModelList.size
    }
}
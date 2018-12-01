package io.jobtrends.jobtrends.viewmodels

import android.support.v4.view.ViewPager.OnPageChangeListener
import io.jobtrends.jobtrends.R.raw.data_boarding
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.HOME_STATE
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.NEXT_STATE
import io.jobtrends.jobtrends.activities.PagerManager
import io.jobtrends.jobtrends.dagger.App.Companion.component
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.BoardingViewModel.BoardingListKey.BOARDING_LIST_KEY
import javax.inject.Inject

class BoardingViewModel : ViewModel, OnPageChangeListener {

    enum class BoardingListKey : ListKey {
        BOARDING_LIST_KEY
    }

    @Inject
    lateinit var rawManager: RawManager
    @Inject
    lateinit var jsonManager: JsonManager
    private var activityManager: PagerManager? = null
    private var currentItem: Int
    override var container: MutableMap<ListKey, MutableList<Model>> = mutableMapOf()

    init {
        component.inject(this)
        currentItem = 0
        val data = rawManager.readRaw(data_boarding)
        container[BOARDING_LIST_KEY] = jsonManager.deserialize(data)
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
        if (index == getCount(BOARDING_LIST_KEY)) {
            activityManager?.setState(HOME_STATE)
        } else if (index >= 0 && index < getCount(BOARDING_LIST_KEY)) {
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

    override fun getItem(key: ListKey, index: Int): Model {
        return container[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return container[key]!!.size
    }
}
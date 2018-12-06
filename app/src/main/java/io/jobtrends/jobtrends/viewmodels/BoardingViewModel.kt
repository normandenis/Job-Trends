package io.jobtrends.jobtrends.viewmodels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v4.view.ViewPager.OnPageChangeListener
import io.jobtrends.jobtrends.R.raw.data_boarding
import io.jobtrends.jobtrends.activities.ActivityManager
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.HOME_STATE
import io.jobtrends.jobtrends.activities.BoardingActivity.BoardingState.NEXT_STATE
import io.jobtrends.jobtrends.activities.PagerManager
import io.jobtrends.jobtrends.adapters.AdapterManager
import io.jobtrends.jobtrends.adapters.ListChangedAdapter
import io.jobtrends.jobtrends.dagger.App.Companion.component
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.BoardingModel
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.BoardingViewModel.BoardingListKey.BOARDING_LIST_KEY
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class BoardingViewModel : ViewModel, OnPageChangeListener {

    enum class BoardingListKey : ListKey {
        BOARDING_LIST_KEY
    }

    @Inject
    lateinit var rawManager: RawManager
    @Inject
    lateinit var jsonManager: JsonManager

    override lateinit var activity: ActivityManager
    override var adapters: MutableMap<ListKey, AdapterManager> = mutableMapOf()
    override var lists: MutableMap<ListKey, ObservableList<Model>> = mutableMapOf()

    private var currentItem: Int

    init {
        component.inject(this)
        currentItem = 0
        val data = rawManager.readRaw(data_boarding)
        val tmp = jsonManager.deserialize<Array<BoardingModel>>(data)
        lists[BOARDING_LIST_KEY] = ObservableArrayList()
        lists[BOARDING_LIST_KEY]!!.addAll(tmp)
    }

    override fun onPageScrollStateChanged(index: Int) {
        currentItem = index
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(index: Int) {
        currentItem = index
        (activity as PagerManager).setButtonText()
    }

    fun onClickSkip() {
        activity.setState(HOME_STATE)
        activity.build()
    }

    fun onClickNext() {
        val index = currentItem + 1
        if (index == getCount(BOARDING_LIST_KEY)) {
            activity.setState(HOME_STATE)
        } else if (index >= 0 && index < getCount(BOARDING_LIST_KEY)) {
            activity.setState(NEXT_STATE)
        }
        activity.build()
    }

    override fun registerActivityManager(activity: ActivityManager) {
        this.activity = activity
    }

    override fun registerAdapterManager(key: ListKey, adapter: AdapterManager) {
        adapters[key] = adapter
        lists[key]?.addOnListChangedCallback(adapter as ListChangedAdapter)
    }

    override fun getItem(key: ListKey, index: Int): Model {
        return lists[key]!![index]
    }

    override fun getCount(key: ListKey): Int {
        return lists[key]!!.size
    }
}
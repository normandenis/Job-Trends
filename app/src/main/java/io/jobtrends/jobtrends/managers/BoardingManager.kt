package io.jobtrends.jobtrends.managers

import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.models.BoardingModel
import javax.inject.Inject

class BoardingManager {

    @Inject
    lateinit var rawManager: RawManager

    @Inject
    lateinit var jsonManager: JsonManager

    private val boardingModelList: Array<BoardingModel>

    init {
        App.component.inject(this)
        val data = rawManager.readRaw(R.raw.data_boarding)
        boardingModelList = jsonManager.deserialize(data)
    }

    fun getItem(index: Int): BoardingModel {
        return boardingModelList[index]
    }

    fun getCount(): Int {
        return boardingModelList.size
    }
}
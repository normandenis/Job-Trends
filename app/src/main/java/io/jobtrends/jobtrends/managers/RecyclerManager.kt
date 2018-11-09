package io.jobtrends.jobtrends.managers

interface RecyclerManager {

    fun getItem(index: Int): Any

    fun getCount(): Int
}
package io.jobtrends.jobtrends.adapters

import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.widget.BaseAdapter
import io.jobtrends.jobtrends.models.Model

class ListChangedAdapter(private val adapter: RecyclerAdapter) :
    OnListChangedCallback<ObservableList<Model>>(), AdapterManager {

    override fun onChanged(sender: ObservableList<Model>) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeChanged(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun onItemRangeInserted(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<Model>,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount)
    }
}

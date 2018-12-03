package io.jobtrends.jobtrends.adapters

import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import io.jobtrends.jobtrends.models.Model

class ListChangedAdapter(private val adapter: AdapterManager) :
    OnListChangedCallback<ObservableList<Model>>(), AdapterManager {

    override fun onChanged(sender: ObservableList<Model>) {
        if (adapter is RecyclerAdapter) {
            adapter.notifyDataSetChanged()
        } else if (adapter is AutoCompleteAdapter) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemRangeChanged(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        if (adapter is RecyclerAdapter) {
            adapter.notifyItemRangeChanged(positionStart, itemCount)
        } else if (adapter is AutoCompleteAdapter) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemRangeInserted(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        if (adapter is RecyclerAdapter) {
            adapter.notifyItemRangeInserted(positionStart, itemCount)
        } else if (adapter is AutoCompleteAdapter) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemRangeMoved(
        sender: ObservableList<Model>,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
        if (adapter is RecyclerAdapter) {
            adapter.notifyItemMoved(fromPosition, toPosition)
        } else if (adapter is AutoCompleteAdapter) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<Model>,
        positionStart: Int,
        itemCount: Int
    ) {
        if (adapter is RecyclerAdapter) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount)
        } else if (adapter is AutoCompleteAdapter) {
            adapter.notifyDataSetChanged()
        }
    }
}

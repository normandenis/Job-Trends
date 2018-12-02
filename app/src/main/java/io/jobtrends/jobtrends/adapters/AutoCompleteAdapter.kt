package io.jobtrends.jobtrends.adapters

import android.content.Context
import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.widget.ArrayAdapter
import android.widget.Filter
import io.jobtrends.jobtrends.R.id.text_0
import io.jobtrends.jobtrends.R.layout.custom_item
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.ListKey
import io.jobtrends.jobtrends.viewmodels.ViewModel


class AutoCompleteAdapter(
    context: Context,
    private val viewModel: ViewModel,
    private val key: ListKey
) : ArrayAdapter<Model>(context, custom_item, text_0), AdapterManager {

    init {
        viewModel.registerAdapterManager(key, AutoCompleteCallback(this))
    }

    override fun getItem(position: Int): Model? {
        return viewModel.getItem(key, position)
    }

    override fun getCount(): Int {
        return viewModel.getCount(key)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    filterResults.values = viewModel.lists[key]
                    filterResults.count = viewModel.lists[key]!!.size
                }
                return filterResults
            }

            override fun publishResults(contraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

}

class AutoCompleteCallback(private val adapter: AutoCompleteAdapter) :
    OnListChangedCallback<ObservableList<Model>>(), AdapterManager {

    override fun onChanged(sender: ObservableList<Model>?) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<Model>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeMoved(
        sender: ObservableList<Model>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeInserted(
        sender: ObservableList<Model>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeChanged(
        sender: ObservableList<Model>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyDataSetChanged()
    }
}
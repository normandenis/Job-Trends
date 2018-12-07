package io.jobtrends.jobtrends.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import io.jobtrends.jobtrends.R.id.text_0
import io.jobtrends.jobtrends.R.layout.custom_item
import io.jobtrends.jobtrends.models.Model
import io.jobtrends.jobtrends.viewmodels.AutoCompleteListener
import io.jobtrends.jobtrends.viewmodels.ListKey
import io.jobtrends.jobtrends.viewmodels.ViewModel


class AutoCompleteAdapter(
    context: Context,
    private val viewModel: ViewModel,
    private val key: ListKey
) : ArrayAdapter<Model>(context, custom_item, text_0), AdapterManager {

    init {
        viewModel.registerAdapterManager(key, ListChangedAdapter(this))
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
                (viewModel as AutoCompleteListener).searchJob()
            }
        }
    }
}
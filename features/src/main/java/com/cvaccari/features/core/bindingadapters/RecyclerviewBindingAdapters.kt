package com.cvaccari.features.core.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.data.model.ShowWrapper
import com.cvaccari.features.search.presentation.ShowsAdapter

@BindingAdapter("entries")
fun RecyclerView.provideShowsAdapter(
    entries: List<ShowInfoModel>? = null
) {
    entries?.let {
        provideShowAdapter().addAddItemAndSubmitList(it)
    }
}

private fun RecyclerView.provideShowAdapter(
): ShowsAdapter {
    if(adapter == null) {
        adapter = ShowsAdapter()
    }

    return adapter as ShowsAdapter
}
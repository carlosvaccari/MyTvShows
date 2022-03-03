package com.cvaccari.features.core.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.core_views.decorators.DividerItemDecoration
import com.cvaccari.features.core.listeners.OnShowClickedListener
import com.cvaccari.core_views.stickyrecyclerview.RecyclerViewStickyHeader
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.core.listeners.OnEpisodeClickedListener
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.presentation.ShowsAdapter
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import com.cvaccari.features.showdetails.presentation.SeasonsEpisodesAdapter

@BindingAdapter(value = ["entries", "onItemClicked"])
fun RecyclerView.provideShowsAdapter(
    entries: List<ShowInfoModel>? = null,
    onItemClicked: OnShowClickedListener? = null,
) {
    entries?.let {
        provideShowAdapter(onItemClicked).addAddItemAndSubmitList(it)
    }
}

private fun RecyclerView.provideShowAdapter(
    onItemClicked: OnShowClickedListener? = null
): ShowsAdapter {
    if (adapter == null) {
        adapter = ShowsAdapter(onItemClicked)
    }

    return adapter as ShowsAdapter
}

@BindingAdapter(value = ["seasonsEntries", "onItemClicked"])
fun RecyclerViewStickyHeader.provideSeasosAdapter(
    entries: List<Section>? = null,
    onItemClicked: OnEpisodeClickedListener
) {
    entries?.let {
        provideSeasonEpisodesAdapter(onItemClicked).list = it
    }

}

private fun RecyclerViewStickyHeader.provideSeasonEpisodesAdapter(
    onItemClicked: OnEpisodeClickedListener
): SeasonsEpisodesAdapter {
    if (adapter == null) {
        setStickAdapter(SeasonsEpisodesAdapter(onItemClicked))
    }

    addItemDecoration(
        DividerItemDecoration(
            context
        )
    )

    return adapter as SeasonsEpisodesAdapter
}

@BindingAdapter(value = ["episodesEntries", "onItemClicked"], requireAll = false)
fun RecyclerView.provideEpisodesAdapter(
    entries: List<ShowDetailsModel>? = null,
    onItemClicked: OnShowClickedListener? = null,
) {
    entries?.let {
        val x= entries.map {
            ShowInfoModel(
                image = it.image,
                name = it.name,
                id = it.id.toString(),
                type = "",
                genres = listOf(),
                summary = ""

            )
        }
        provideShowAdapter(onItemClicked).addAddItemAndSubmitList(x)
    }
}
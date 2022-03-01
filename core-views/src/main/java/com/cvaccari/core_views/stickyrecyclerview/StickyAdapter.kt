package com.cvaccari.core_views.stickyrecyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class StickyAdapter<SVH : RecyclerView.ViewHolder, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    abstract fun getHeaderPositionForItem(itemPosition: Int): Int

    abstract fun getHeaderListSize(): Int

    abstract fun getHeaderItems(): List<Section>

    abstract fun onBindHeaderViewHolder(
        holder: RecyclerView.ViewHolder,
        headerPosition: Int,
        items: List<Section>
    )

    abstract fun onCreateHeaderViewHolder(parent: ViewGroup): SVH
}

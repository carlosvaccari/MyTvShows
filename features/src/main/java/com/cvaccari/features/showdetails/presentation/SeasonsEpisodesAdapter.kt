package com.cvaccari.features.showdetails.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.core_views.stickyrecyclerview.StickyAdapter
import com.cvaccari.features.R
import com.cvaccari.features.core.listeners.OnEpisodeClickedListener
import com.cvaccari.features.databinding.SeasonsEpisodesHeaderBinding
import com.cvaccari.features.databinding.SeasonsEpisodesItemBinding
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import com.cvaccari.features.showdetails.presentation.model.ShowSeasonHeaderSectionModel
import com.cvaccari.features.showdetails.presentation.model.ShowSeasonItemSectionModel

class SeasonsEpisodesAdapter(
    val listener: OnEpisodeClickedListener
): StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>() {

    var list = listOf<Section>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].type() == Section.ITEM) {
            (holder as ViewHolderItem).bind((list[position] as ShowSeasonItemSectionModel).item, listener)
        } else {
            (holder as ViewHolderHeader).bind(list[position] as ShowSeasonHeaderSectionModel)
        }
    }

    override fun getHeaderItems() = list.filter { it.type() == Section.HEADER }

    override fun getHeaderPositionForItem(itemPosition: Int) = list[itemPosition].sectionPosition()

    override fun getHeaderListSize() = getHeaderItems().size

    override fun onBindHeaderViewHolder(
        holder: RecyclerView.ViewHolder,
        headerPosition: Int,
        items: List<Section>
    ) {
        (holder as ViewHolderHeader).bind(
            (getHeaderItems()[headerPosition] as ShowSeasonHeaderSectionModel)
        )
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        createViewHolder(
            parent,
            Section.HEADER
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Section.HEADER -> ViewHolderHeader(
                SeasonsEpisodesHeaderBinding.bind(
                    inflater.inflate(
                        R.layout.seasons_episodes_header,
                        parent,
                        false
                    )
                )
            )
            else -> ViewHolderItem(
                SeasonsEpisodesItemBinding.bind(
                    inflater.inflate(
                        R.layout.seasons_episodes_item,
                        parent,
                        false
                    )
                )
            )
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = list[position].type()

    class ViewHolderHeader(private val binding: SeasonsEpisodesHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(details: ShowSeasonHeaderSectionModel) {
            binding.seasonTitle = details.sectionTitle
            binding.executePendingBindings()
        }
    }

    class ViewHolderItem(private val binding: SeasonsEpisodesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ShowDetailsModel,
            listener: OnEpisodeClickedListener
        ) {
            binding.item = item
            binding.onClickListener = listener
            binding.executePendingBindings()
        }
    }
}
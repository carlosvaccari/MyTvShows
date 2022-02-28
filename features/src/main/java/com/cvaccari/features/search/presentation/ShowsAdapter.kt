package com.cvaccari.features.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.databinding.ShowInfoItemBinding
import com.cvaccari.features.search.data.model.ShowWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowsAdapter: ListAdapter<ShowWrapper, ShowsAdapter.ShowInfoViewHolder>(ShowsItemDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addAddItemAndSubmitList(list: List<ShowWrapper>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowInfoViewHolder {
        return ShowInfoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShowInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ShowInfoViewHolder private constructor(private val binding: ShowInfoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(showWrapper: ShowWrapper) {
            binding.showInfo = showWrapper.show
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ShowInfoViewHolder {
                val binding = ShowInfoItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ShowInfoViewHolder(binding)
            }
        }
    }
}

class ShowsItemDiffCallback: DiffUtil.ItemCallback<ShowWrapper>() {
    override fun areItemsTheSame(
        oldItem: ShowWrapper, newItem: ShowWrapper
    ): Boolean {
        return oldItem.show.id == newItem.show.id
    }

    override fun areContentsTheSame(
        oldItem: ShowWrapper, newItem: ShowWrapper
    ): Boolean {
        return oldItem.show == newItem.show
    }
}
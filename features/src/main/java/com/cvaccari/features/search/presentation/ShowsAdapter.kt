package com.cvaccari.features.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.core.listeners.OnShowClickedListener
import com.cvaccari.features.databinding.ShowInfoItemBinding
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowsAdapter(val onItemClicked: OnShowClickedListener?) :
    ListAdapter<ShowInfoModel, ShowsAdapter.ShowInfoViewHolder>(ShowsItemDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addAddItemAndSubmitList(list: List<ShowInfoModel>) {
        if (list.isEmpty()) {
            submitList(list)
        } else {
            adapterScope.launch {
                withContext(Dispatchers.Main) {
                    submitList(currentList + list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowInfoViewHolder {
        return ShowInfoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShowInfoViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class ShowInfoViewHolder private constructor(private val binding: ShowInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(showWrapper: ShowInfoModel, onItemClicked: OnShowClickedListener?) {
            binding.showInfo = showWrapper
            binding.onClickListener = onItemClicked
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

class ShowsItemDiffCallback : DiffUtil.ItemCallback<ShowInfoModel>() {
    override fun areItemsTheSame(
        oldItem: ShowInfoModel, newItem: ShowInfoModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShowInfoModel, newItem: ShowInfoModel
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
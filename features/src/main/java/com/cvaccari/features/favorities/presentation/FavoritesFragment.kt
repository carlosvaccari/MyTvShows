package com.cvaccari.features.favorities.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.FavoritesFragmentBinding
import com.cvaccari.features.search.data.model.ShowInfoModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : BaseFragment(R.layout.favorites_fragment) {

    private val viewModel: FavoritesViewModel by viewModel()
    private val binding: FavoritesFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        initObservers()
    }

    private fun initObservers() {
        viewModel.states.observe(viewLifecycleOwner, Observer {
            when (it) {
                is FavoritesStates.ShowDetails -> showDetails(it.item)
            }
        })
    }

    private fun showDetails(item: ShowInfoModel) {
        findNavController().navigate(FavoritesFragmentDirections.actionNavigationNotificationsToNavigationShowDetails(
            item
        ))
    }
}
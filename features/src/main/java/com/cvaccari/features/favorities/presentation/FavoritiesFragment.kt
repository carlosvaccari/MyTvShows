package com.cvaccari.features.favorities.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.FavoritiesFragmentBinding
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.di.SearchModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritiesFragment : BaseFragment(R.layout.favorities_fragment) {

    override var module = SearchModule.instance
    private val viewModel: FavoritiesViewModel by viewModel()
    private val binding: FavoritiesFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel) //TODO maybe we dont need
        initObservers()
    }

    private fun initObservers() {
        viewModel.states.observe(viewLifecycleOwner, Observer {
            when (it) {
                is FavoritiesStates.ShowDetails -> showDetails(it.item)
            }
        })
    }

    private fun showDetails(item: ShowInfoModel) {
        findNavController().navigate(FavoritiesFragmentDirections.actionNavigationNotificationsToNavigationShowDetails(
            item
        ))
    }
}
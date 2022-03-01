package com.cvaccari.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.HomeFragmentBinding
import com.cvaccari.features.home.di.HomeModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {

    override var module = HomeModule.instance
    private val viewModel: HomeViewModel by viewModel()
    private val binding: HomeFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        initObservers()
    }

    private fun initObservers() {
        viewModel.states.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HomeStates.ShowDetails -> showDetails(it.id)
            }
        })
    }

    private fun showDetails(id: String) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationShowDetails(
            id
        ))
    }
}
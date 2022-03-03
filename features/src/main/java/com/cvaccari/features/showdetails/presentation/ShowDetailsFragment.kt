package com.cvaccari.features.showdetails.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.ShowDetailsFragmentBinding
import com.cvaccari.features.showdetails.di.ShowDetailsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment : BaseFragment(R.layout.show_details_fragment) {

    override var module: Module = ShowDetailsModule.instance
    private val binding: ShowDetailsFragmentBinding by dataBinding()
    private val args by navArgs<ShowDetailsFragmentArgs>()
    private val viewModel: ShowDetailsViewModel by viewModel { parametersOf(args.showInfoModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.onClickListener = viewModel.onFavoriteClicked
        lifecycle.addObserver(viewModel)
        initObserver()
    }

    private fun initObserver() {
        viewModel.states.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ShowDetailsStates.ShowEpisodeDetails -> findNavController().navigate(
                    ShowDetailsFragmentDirections.actionNavigationShowDetailsToNavigationEpisodeDetails(
                        it.showId,
                        it.season.toString(),
                        it.number.toString()
                    )
                )
            }
        })
    }
}
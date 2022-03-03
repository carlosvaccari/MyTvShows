package com.cvaccari.features.episodedetails.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.EpisodesDetailsFragmentBinding
import com.cvaccari.features.episodedetails.di.EpisodeDetailsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class EpisodeDetailsFragment : BaseFragment(R.layout.episodes_details_fragment) {

    override var module: Module = EpisodeDetailsModule.instance
    private val viewModel: EpisodeDetailsViewModel by viewModel {
        parametersOf(
            args.showId,
            args.season,
            args.number
        )
    }
    private val binding: EpisodesDetailsFragmentBinding by dataBinding()
    private val args by navArgs<EpisodeDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
    }
}

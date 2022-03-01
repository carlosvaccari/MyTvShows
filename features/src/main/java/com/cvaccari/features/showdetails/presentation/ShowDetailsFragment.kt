package com.cvaccari.features.showdetails.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.ShowDetailsFragmentBinding
import com.cvaccari.features.showdetails.di.ShowDetailsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment: BaseFragment(R.layout.show_details_fragment) {

    override var module: Module = ShowDetailsModule.instance
    private val binding: ShowDetailsFragmentBinding by dataBinding()
    private val args by navArgs<ShowDetailsFragmentArgs>()
    private val viewModel: ShowDetailsViewModel by viewModel{ parametersOf(args.showId)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
    }
}
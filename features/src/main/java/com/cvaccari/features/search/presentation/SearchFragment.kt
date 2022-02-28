package com.cvaccari.features.search.presentation

import android.os.Bundle
import android.view.View
import com.cvaccari.commons.base.BaseFragment
import com.cvaccari.commons.delegate.dataBinding
import com.cvaccari.features.R
import com.cvaccari.features.databinding.SearchFragmentBinding
import com.cvaccari.features.search.di.SearchModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.search_fragment) {

    override var module = SearchModule.instance
    private val viewModel: SearchViewModel by viewModel()
    private val binding: SearchFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel) //TODO maybe we dont need
    }
}
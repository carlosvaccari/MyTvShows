package com.cvaccari.features.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.utils.DebouncingQueryTextListener
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.data.model.ShowWrapper
import com.cvaccari.features.search.domain.SearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val useCase: SearchUseCase
) : BaseViewModel() {

    private val _showsItems = MutableLiveData<List<ShowWrapper>>()
    val showsItems: LiveData<List<ShowWrapper>>
        get() = _showsItems

    private fun onQueryChanged(query: String) {
        viewModelScope.launch {
            useCase.querySeries(query)
                .onSuccess {
                    _showsItems.postValue(it)
                }
                .onFailure {

                }
        }
    }

    val onQueryTextListener = DebouncingQueryTextListener(viewModelScope.coroutineContext) {
        it?.let { onQueryChanged(query = it) }
    }
}

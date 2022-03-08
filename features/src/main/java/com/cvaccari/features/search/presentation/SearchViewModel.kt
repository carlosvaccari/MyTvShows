package com.cvaccari.features.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.utils.DebouncingQueryTextListener
import com.cvaccari.commons.utils.SingleLiveEvent
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.features.core.listeners.OnShowClickedListener
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.domain.SearchUseCase
import kotlinx.coroutines.launch

sealed class SearchStates {
    object Loading : SearchStates()
    object Success : SearchStates()
    object Error : SearchStates()
    data class ShowDetails(val item : ShowInfoModel): SearchStates()
}

class SearchViewModel(
    private val useCase: SearchUseCase
) : BaseViewModel() {

    private val _states = SingleLiveEvent<SearchStates>()
    val states : LiveData<SearchStates>
        get() = _states

    private val _showsItems = MutableLiveData<List<ShowInfoModel>>()
    val showsItems: LiveData<List<ShowInfoModel>>
        get() = _showsItems

    val onItemClicked = object : OnShowClickedListener {
        override fun onClicked(item: ShowInfoModel) {
            _states.postValue(SearchStates.ShowDetails(item))
        }
    }

    private fun onQueryChanged(query: String) {
        _states.value = SearchStates.Loading
        _showsItems.value = listOf()
        viewModelScope.launch {
            useCase.querySeries(query)
                .onSuccess {
                    _showsItems.postValue(it)
                    _states.value = SearchStates.Success
                }
                .onFailure {
                    _states.value = SearchStates.Error
                }
        }
    }

    val onQueryTextListener = DebouncingQueryTextListener(viewModelScope.coroutineContext,
        onDebouncingQueryTextChange = { it?.let { onQueryChanged(query = it) } },
        onClearTextClicked = { _showsItems.postValue(listOf()) }
    )

    fun isLoading() = Transformations.map(_states) { it is SearchStates.Loading }

    fun isError() = Transformations.map(_states) { it is SearchStates.Error}
}

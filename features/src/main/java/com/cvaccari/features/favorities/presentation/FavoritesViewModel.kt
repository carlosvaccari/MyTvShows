package com.cvaccari.features.favorities.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.utils.SingleLiveEvent
import com.cvaccari.features.core.listeners.OnShowClickedListener
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

sealed class FavoritesStates {
    object Loading : FavoritesStates()
    object Success : FavoritesStates()
    object Empty : FavoritesStates()
    data class ShowDetails(val item: ShowInfoModel) : FavoritesStates()
}

class FavoritesViewModel(
    private val useCase: FavoritesUseCase
) : BaseViewModel() {

    private val _states = SingleLiveEvent<FavoritesStates>()
    val states: LiveData<FavoritesStates>
        get() = _states

    private val _showsItems = MutableLiveData<List<ShowInfoModel>>()
    val showsItems: LiveData<List<ShowInfoModel>>
        get() = _showsItems

    val onItemClicked = object : OnShowClickedListener {
        override fun onClicked(item: ShowInfoModel) {
            _states.postValue(FavoritesStates.ShowDetails(item))
        }
    }

    override fun onCreate() {
        super.onCreate()
        _states.postValue(FavoritesStates.Loading)
        useCase.getFavorites()
            .onEach {
                if(it.isEmpty()) {
                    _states.postValue(FavoritesStates.Empty)
                } else {
                    _states.postValue(FavoritesStates.Success)
                }
                _showsItems.value = listOf()
                _showsItems.postValue(it)
            }.launchIn(viewModelScope)
    }

    fun isLoading() = Transformations.map(_states) { it is FavoritesStates.Loading }

    fun isEmpty() = Transformations.map(_states) { it is FavoritesStates.Empty }

}
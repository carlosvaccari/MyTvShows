package com.cvaccari.features.favorities.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.utils.SingleLiveEvent
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.presentation.SearchStates
import kotlinx.coroutines.launch

sealed class FavoritiesStates {
    object Loading : FavoritiesStates()
    object Success : FavoritiesStates()
    data class ShowDetails(val item : ShowInfoModel): FavoritiesStates()
}

class FavoritiesViewModel(
    private val useCase: FavoritiesUseCase
): BaseViewModel() {

    private val _states = SingleLiveEvent<FavoritiesStates>()
    val states : LiveData<FavoritiesStates>
        get() = _states

    override fun onCreate() {
        super.onCreate()

        viewModelScope.launch {

        }
    }
}
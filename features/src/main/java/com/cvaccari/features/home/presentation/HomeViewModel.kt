package com.cvaccari.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.base.State
import com.cvaccari.commons.error.ErrorActionsListener
import com.cvaccari.commons.utils.SingleLiveEvent
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.core_views.recyclerview.PagingRecyclerView
import com.cvaccari.features.core.listeners.OnShowClickedListener
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.home.domain.HomeUseCase
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class HomeStates : State {
    data class ShowDetails(val item: ShowInfoModel) : HomeStates()
    object Loading : HomeStates()
    object Success : HomeStates()
    object Error : HomeStates()
}

class HomeViewModel(
    private val useCase: HomeUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : BaseViewModel() {

    private val _states = SingleLiveEvent<HomeStates>()
    val states: SingleLiveEvent<HomeStates>
        get() = _states

    private val _showsItems = MutableLiveData<List<ShowInfoModel>>()
    val showsItems: LiveData<List<ShowInfoModel>>
        get() = _showsItems

    val listPagingListener = object : PagingRecyclerView.LoadMoreListener {
        override fun loadMore() {
            getSeries()
        }
    }

    val onItemClicked = object : OnShowClickedListener {
        override fun onClicked(item: ShowInfoModel) {
            _states.postValue(HomeStates.ShowDetails(item))
        }
    }

    override fun onCreate() {
        super.onCreate()
        getSeries()
    }

    private fun getSeries() {
        _states.postValue(HomeStates.Loading)

        viewModelScope.launch {
            useCase.getSeries()
                .onSuccess {
                    _states.postValue(HomeStates.Success)
                    _showsItems.postValue(it)
                }
                .onFailure {
                    _states.postValue(HomeStates.Error)
                }
        }

        favoritesUseCase.getFavorites()
            .flatMapMerge {
                favoritesUseCase.enrichWithFavorites(_showsItems.value ?: listOf())
            }.onEach {
                _showsItems.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    val errorActionsListener = object : ErrorActionsListener {
        override fun onTryAgainClicked() {
            getSeries()
        }
    }

    fun isError() = Transformations.map(_states) { it is HomeStates.Error }

    fun isLoading() = Transformations.map(_states) { it is HomeStates.Loading }

}

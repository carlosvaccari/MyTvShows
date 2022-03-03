package com.cvaccari.features.showdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.commons.utils.SingleLiveEvent
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.core.listeners.OnEpisodeClickedListener
import com.cvaccari.features.core.listeners.OnFavoriteClickedListener
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import com.cvaccari.features.showdetails.domain.ShowDetailsUseCase
import com.cvaccari.features.showdetails.presentation.model.ShowDetailsPresentationModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class ShowDetailsStates {
    object Loading : ShowDetailsStates()
    object Success : ShowDetailsStates()
    data class ShowEpisodeDetails(val showId: String, val season: Int, val number: Int) : ShowDetailsStates()
}

class ShowDetailsViewModel(
    private val show: ShowInfoModel,
    private val useCase: ShowDetailsUseCase,
    private val favoritesUseCase: FavoritesUseCase
) : BaseViewModel() {

    private var _states = SingleLiveEvent<ShowDetailsStates>()
    val states: LiveData<ShowDetailsStates>
        get() = _states

    private var _seasonsList = MutableLiveData<List<Section>>()
    val seasonsList: LiveData<List<Section>>
        get() = _seasonsList

    private var _showDetails = MutableLiveData<ShowDetailsPresentationModel>()
    val showDetails: LiveData<ShowDetailsPresentationModel>
        get() = _showDetails

    private var _showName = MutableLiveData<String>()
    val showName: LiveData<String>
        get() = _showName

    private var _isFavorite= MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    override fun onCreate() {
        super.onCreate()
        _states.value = ShowDetailsStates.Loading
        viewModelScope.launch {
            useCase.getShowDetails(show.id)
                .onSuccess {
                    _showName.value = show.name
                    _seasonsList.value = it.seasonsList
                    _showDetails.value = it
                    _states.value = ShowDetailsStates.Success
                }
                .onFailure {
                    it.printStackTrace()
                }
        }

        favoritesUseCase.getFavoriteById(show.id.toInt())
            .onEach {
                show.isFavorite = it?.isFavorite ?: false
                _isFavorite.postValue(it?.isFavorite)
            }.launchIn(viewModelScope)
    }

    val onFavoriteClicked = object : OnFavoriteClickedListener {
        override fun onAddToFavorite() {
            viewModelScope.launch {
                favoritesUseCase.handleFavorite(show)
            }
        }
    }

    val onEpisodeClicked = object : OnEpisodeClickedListener {
        override fun onClick(item : ShowDetailsModel) {
            _states.postValue(ShowDetailsStates.ShowEpisodeDetails(show.id, item.season, item.number))
        }
    }

    fun isLoading() = Transformations.map(_states) { it is ShowDetailsStates.Loading }
}

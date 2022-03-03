package com.cvaccari.features.episodedetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.features.episodedetails.data.model.EpisodeResponseModel
import com.cvaccari.features.episodedetails.domain.EpisodeDetailsUseCase
import kotlinx.coroutines.launch

sealed class EpisodeDetailsStates {
    object Loading : EpisodeDetailsStates()
    object Success : EpisodeDetailsStates()
}

class EpisodeDetailsViewModel(
    private val showId: String,
    private val season: String,
    private val number: String,
    private val useCase: EpisodeDetailsUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<EpisodeDetailsStates>()
    val states: LiveData<EpisodeDetailsStates>
        get() = _state

    private val _episodeModel = MutableLiveData<EpisodeResponseModel>()
    val episodeModel: LiveData<EpisodeResponseModel>
        get() = _episodeModel

    override fun onCreate() {
        super.onCreate()
        viewModelScope.launch {
            useCase.getEpisodeByNumber(
                showId = showId.toInt(),
                season = season.toInt(),
                number = number.toInt()
            ).onSuccess {
                _episodeModel.postValue(it)
                _state.postValue(EpisodeDetailsStates.Success)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}
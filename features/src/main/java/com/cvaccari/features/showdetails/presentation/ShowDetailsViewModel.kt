package com.cvaccari.features.showdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.showdetails.domain.ShowDetailsUseCase
import kotlinx.coroutines.launch

class ShowDetailsViewModel(
    private val showId: String,
    private val useCase: ShowDetailsUseCase
) : BaseViewModel() {

    private var _seasonsList = MutableLiveData<List<Section>>()
    val seasonsList: LiveData<List<Section>>
            get() = _seasonsList

    override fun onCreate() {
        super.onCreate()
        viewModelScope.launch {
            useCase.getShowDetails(showId)
                .onSuccess {
                    _seasonsList.postValue(it.seasonsList)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }
}

package com.cvaccari.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cvaccari.commons.base.BaseViewModel
import com.cvaccari.core_network.networkresponse.onFailure
import com.cvaccari.core_network.networkresponse.onSuccess
import com.cvaccari.features.home.domain.HomeUseCase
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: HomeUseCase
) : BaseViewModel() {

    private val _showsItems = MutableLiveData<List<ShowInfoModel>>()
    val showsItems: LiveData<List<ShowInfoModel>>
        get() = _showsItems

    override fun onCreate() {
        super.onCreate()
        viewModelScope.launch {
            useCase.getSeries()
                .onSuccess {
                    _showsItems.postValue(it)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }
}
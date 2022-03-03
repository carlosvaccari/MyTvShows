package com.cvaccari.features.home.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

interface HomeUseCase {
    suspend fun getSeries() : ResultWrapper<List<ShowInfoModel>>
}

class HomeUseCaseImpl(
    private val repository: HomeRepository,
) : HomeUseCase {

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return repository.getSeries()
    }
}
package com.cvaccari.features.home.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.search.data.model.ShowInfoModel

interface HomeUseCase {
    suspend fun getSeries() : ResultWrapper<List<ShowInfoModel>>
}

class HomeUseCaseImpl(
    private val repository: HomeRepository
): HomeUseCase {

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return repository.getSeries()
    }
}
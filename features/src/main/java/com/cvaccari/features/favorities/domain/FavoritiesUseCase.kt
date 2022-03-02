package com.cvaccari.features.favorities.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.favorities.data.FavoritiesRepository
import com.cvaccari.features.search.data.model.ShowInfoModel

interface FavoritiesUseCase {
    suspend fun getFavorities(): ResultWrapper<ShowInfoModel>
}

class FavoritiesUseCaseImpl(
    private val repository: FavoritiesRepository
): FavoritiesUseCase {

    override suspend fun getFavorities(): ResultWrapper<ShowInfoModel> {
        return repository.getFavorities()
    }

}
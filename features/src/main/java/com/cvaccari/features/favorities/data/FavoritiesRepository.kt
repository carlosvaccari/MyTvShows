package com.cvaccari.features.favorities.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.search.data.model.ShowInfoModel

interface FavoritiesRepository {
    suspend fun getFavorities(): ResultWrapper<List<ShowInfoModel>>
}

class FavoritiesRepositoryImpl(
    private val localDataSource: FavoritiesDataSource
): FavoritiesRepository {

    override suspend fun getFavorities(): ResultWrapper<List<ShowInfoModel>> {
        return localDataSource.getFavorities()
    }
}
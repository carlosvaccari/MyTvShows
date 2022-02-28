package com.cvaccari.features.home.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.search.data.model.ShowInfoModel

interface HomeRepository {
    suspend fun getSeries() : ResultWrapper<List<ShowInfoModel>>
}

class HomeRepositoryImpl(
    private val api: HomeApi
): HomeRepository {

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return api.getSeries(1).toResult()
    }
}
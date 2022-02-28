package com.cvaccari.features.home.data

import com.cvaccari.commons.utils.PageController
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.search.data.model.ShowInfoModel

interface HomeRepository {
    suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>>
}

class HomeRepositoryImpl(
    private val api: HomeApi
) : HomeRepository, PageController() {

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return registerNetworkRequest(api.getSeries(currentPage))
    }

}
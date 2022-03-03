package com.cvaccari.features.home.data

import com.cvaccari.commons.utils.PageController
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.favorities.data.FavoritesLocalDataSource
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.first

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
package com.cvaccari.features.home.data

import com.cvaccari.commons.utils.PageController
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.favorities.data.FavoritesLocalDataSource
import com.cvaccari.features.home.data.model.ShowImagesListModel
import com.cvaccari.features.home.data.model.UpdatedShows
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.first

interface HomeRepository {
    suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>>
    suspend fun getWhatsNew(): ResultWrapper<UpdatedShows>
    suspend fun getSingleShowInfo(showId: Int): ResultWrapper<ShowInfoModel>
    suspend fun getSingleShowImages(showId: Int): ResultWrapper<List<ShowImagesListModel>>
}

class HomeRepositoryImpl(
    private val api: HomeApi
) : HomeRepository, PageController() {

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return registerNetworkRequest(api.getSeries(currentPage))
    }

    override suspend fun getSingleShowImages(showId: Int): ResultWrapper<List<ShowImagesListModel>> {
        return api.getSingleShowImages(showId).toResult()
    }

    override suspend fun getWhatsNew(): ResultWrapper<UpdatedShows> {
        return api.getUpdates().toResult()
    }

    override suspend fun getSingleShowInfo(showId: Int): ResultWrapper<ShowInfoModel> {
        return api.getSingleShowInfo(showId).toResult()
    }

}
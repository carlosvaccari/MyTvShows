package com.cvaccari.features.showdetails.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel

interface ShowDetailsRepository {
    suspend fun getShowDetails(showId: String) : ResultWrapper<ShowInfoModel>
}

class ShowDetailsRepositoryImpl(
    private val api: ShowDetailsApi
): ShowDetailsRepository {

    override suspend fun getShowDetails(showId: String): ResultWrapper<ShowInfoModel> {
        return api.getShowDetails(showId).toResult()
    }
}

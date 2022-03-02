package com.cvaccari.features.favorities.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.search.data.model.ShowInfoModel

interface FavoritiesDataSource {

    fun getFavorities(): ResultWrapper<List<ShowInfoModel>>

    fun addFavority(item: ShowInfoModel): ResultWrapper<ShowInfoModel>

    fun removeFavority(item: ShowInfoModel) : ResultWrapper<ShowInfoModel>
}

class FavoritiesDataSourceImpl(): FavoritiesDataSource {

    override fun getFavorities(): ResultWrapper<List<ShowInfoModel>> {
        TODO("Not yet implemented")
    }

    override fun addFavority(item: ShowInfoModel): ResultWrapper<ShowInfoModel> {
        TODO("Not yet implemented")
    }

    override fun removeFavority(item: ShowInfoModel): ResultWrapper<ShowInfoModel> {
        TODO("Not yet implemented")
    }
}

package com.cvaccari.features.home.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.search.data.model.ShowInfoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("/shows")
    suspend fun getSeries(@Query("page") query: Int): NetworkResponse<List<ShowInfoModel>>
}
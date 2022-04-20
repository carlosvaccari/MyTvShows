package com.cvaccari.features.home.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.home.data.model.ShowImagesListModel
import com.cvaccari.features.home.data.model.UpdatedShows
import com.cvaccari.features.search.data.model.ShowInfoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("/shows/{showId}/images")
    suspend fun getSingleShowImages(@Path("showId") showId: Int): NetworkResponse<List<ShowImagesListModel>>

    @GET("/shows/{showId}")
    suspend fun getSingleShowInfo(@Path("showId") showId: Int): NetworkResponse<ShowInfoModel>

    @GET("/shows")
    suspend fun getSeries(@Query("page") query: Int): NetworkResponse<List<ShowInfoModel>>

    @GET("/updates/shows?since=day")
    suspend fun getUpdates(): NetworkResponse<UpdatedShows>
}
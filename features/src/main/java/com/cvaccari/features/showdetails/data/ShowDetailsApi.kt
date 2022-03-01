package com.cvaccari.features.showdetails.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ShowDetailsApi {

    @GET("/shows/{showId}/episodes")
    suspend fun getShowDetails(@Path("showId") showId: String): NetworkResponse<List<ShowDetailsModel>>

}
package com.cvaccari.features.showdetails.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowDetailsApi {

    @GET("/shows/{showId}")
    suspend fun getShowDetails(@Path("showId") showId: String,
                               @Query("embed") embed: String = "episodes"): NetworkResponse<ShowInfoModel>

}
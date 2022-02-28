package com.cvaccari.features.search.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.search.data.model.ShowWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search/shows")
    suspend fun querySeries(@Query("q") query: String): NetworkResponse<List<ShowWrapper>>
}
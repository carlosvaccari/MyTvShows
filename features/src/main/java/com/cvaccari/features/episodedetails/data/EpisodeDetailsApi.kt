package com.cvaccari.features.episodedetails.data

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.features.episodedetails.data.model.EpisodeResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeDetailsApi {

    @GET("/shows/{id}/episodebynumber")
    suspend fun getEpisodeByNumber(@Path("id") showId: Int, @Query("season") season: Int, @Query("number") number: Int) : NetworkResponse<EpisodeResponseModel>
}
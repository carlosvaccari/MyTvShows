package com.cvaccari.features.episodedetails.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.episodedetails.data.model.EpisodeResponseModel

interface EpisodeDetailsRepository {
    suspend fun getEpisodeByNumber(showId: Int, season: Int, number: Int): ResultWrapper<EpisodeResponseModel>
}

class EpisodeDetailsRepositoryImpl(
    private val api: EpisodeDetailsApi
): EpisodeDetailsRepository {

    override suspend fun getEpisodeByNumber(showId: Int, season: Int, number: Int): ResultWrapper<EpisodeResponseModel> {
        return api.getEpisodeByNumber(showId, season, number).toResult()
    }
}
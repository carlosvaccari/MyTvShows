package com.cvaccari.features.episodedetails.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.episodedetails.data.EpisodeDetailsRepository
import com.cvaccari.features.episodedetails.data.model.EpisodeResponseModel

interface EpisodeDetailsUseCase {
    suspend fun getEpisodeByNumber(showId: Int, season: Int, number: Int): ResultWrapper<EpisodeResponseModel>
}

class EpisodeDetailsUseCaseImpl(
    private val repository: EpisodeDetailsRepository
): EpisodeDetailsUseCase {

    override suspend fun getEpisodeByNumber(
        showId: Int, season: Int, number: Int
    ): ResultWrapper<EpisodeResponseModel> {
        return repository.getEpisodeByNumber(showId, season, number)
    }
}
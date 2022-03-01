package com.cvaccari.features.showdetails.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.showdetails.data.ShowDetailsRepository
import com.cvaccari.features.showdetails.data.model.ShowDetailsModel
import com.cvaccari.features.showdetails.presentation.model.ShowDetailsPresentationModel
import com.cvaccari.features.showdetails.presentation.model.ShowSeasonHeaderSectionModel
import com.cvaccari.features.showdetails.presentation.model.ShowSeasonItemSectionModel

interface ShowDetailsUseCase {
    suspend fun getShowDetails(showId: String): ResultWrapper<ShowDetailsPresentationModel>
}

class ShowDetailsUseCaseImpl(
    private val repository: ShowDetailsRepository
) : ShowDetailsUseCase {

    override suspend fun getShowDetails(showId: String): ResultWrapper<ShowDetailsPresentationModel> {
        val result = repository.getShowDetails(showId)
        if (result.isSuccessful()) {
            return formatData(result)
        } else {
            return ResultWrapper.Error(result.toError().failure)
        }
    }

    private fun formatData(
        result: ResultWrapper<List<ShowDetailsModel>>
    ): ResultWrapper.Success<ShowDetailsPresentationModel> {
        val seasonLists = mutableListOf<Section>()
        val seasons = result.toSuccess().value.groupBy { it.season }
        seasons.keys.forEach {
            val episodes = seasons[it]
            val firstEpisode = episodes!!.first()

            seasonLists.add(
                ShowSeasonHeaderSectionModel(
                    firstEpisode.season.toString(),
                    firstEpisode.id
                )
            )

            seasonLists.addAll(
                episodes.map {
                    ShowSeasonItemSectionModel(
                        it,
                        firstEpisode.season
                    )
                }
            )
        }
        return ResultWrapper.Success(
            ShowDetailsPresentationModel(
                seasonsList = seasonLists,
                name = seasons[1]!![0].name,
                summary = seasons[1]!![0].summary
            )
        )
    }
}
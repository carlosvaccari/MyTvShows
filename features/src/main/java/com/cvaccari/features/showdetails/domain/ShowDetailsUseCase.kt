package com.cvaccari.features.showdetails.domain

import com.cvaccari.commons.extensions.fromHtml
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

    override suspend fun getShowDetails(
        showId: String
    ): ResultWrapper<ShowDetailsPresentationModel> {
        val result = repository.getShowDetails(showId)
        if (result.isSuccessful()) {
            return formatData(result)
        } else {
            return result.toError()
        }
    }

    private fun formatData(
        result: ResultWrapper<List<ShowDetailsModel>>
    ): ResultWrapper.Success<ShowDetailsPresentationModel> {
        val seasonLists = mutableListOf<Section>()
        val seasons = result.toSuccess().value.groupBy { it.season }

        seasons.keys.forEach {
            val season = it - 1
            val episodes = seasons[it]
            val firstEpisode = episodes!!.first()

            seasonLists.add(
                ShowSeasonHeaderSectionModel(
                    firstEpisode.season.toString(),
                    season
                )
            )

            seasonLists.addAll(
                episodes.map { showDetailsModel ->
                    ShowSeasonItemSectionModel(
                        showDetailsModel.copy(summary = showDetailsModel.summary.fromHtml()),
                        season
                    )
                }
            )
        }
        return ResultWrapper.Success(
            ShowDetailsPresentationModel(
                seasonsList = seasonLists,
                name = seasons[1]?.first()?.name.orEmpty(),
                summary = seasons[1]?.first()?.summary?.fromHtml().orEmpty(),
                images = seasons[1]?.first()?.image,
                seasonsCount = seasonLists.filter { it.type() == Section.HEADER }.count(),
            )
        )
    }
}
package com.cvaccari.features.showdetails.domain

import androidx.core.text.HtmlCompat
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
            return ResultWrapper.Error(result.toError().failure)
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
                episodes.map {
                    ShowSeasonItemSectionModel(
                        it,
                        season
                    )
                }
            )
        }
        return ResultWrapper.Success(
            ShowDetailsPresentationModel(
                seasonsList = seasonLists,
                name = seasons[1]!![0].name,
                summary = HtmlCompat.fromHtml(
                    seasons[1]!![0].summary,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ),
                images = seasons[1]!![0].image,
                seasonsCount = seasonLists.filter { it.type() == Section.HEADER }.count()
            )
        )
    }
}
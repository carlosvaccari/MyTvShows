package com.cvaccari.features.home.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.core.enums.ImagesResolutionsEnum
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.home.data.model.ShowImagesListModel
import com.cvaccari.features.home.data.model.UpdatedShows
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

interface HomeUseCase {
    suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>>
    suspend fun getWhatsNew(): ResultWrapper<ShowInfoModel>
}

class HomeUseCaseImpl(
    private val repository: HomeRepository,
) : HomeUseCase {

    override suspend fun getWhatsNew(): ResultWrapper<ShowInfoModel> {
        val updatedShows = repository.getWhatsNew().toSuccess().value.keys.first()
        val image = getSingleShowImage(updatedShows.toInt())
        return image?.let { image ->
            repository.getSingleShowInfo(updatedShows.toInt())
                .map { it.image?.background = image.resolutions.original.url; it }
        } ?: ResultWrapper.Error(Throwable())
    }

    override suspend fun getSeries(): ResultWrapper<List<ShowInfoModel>> {
        return repository.getSeries()
    }

    private suspend fun getSingleShowImage(showId: Int): ShowImagesListModel? {
        val result = repository.getSingleShowImages(showId)
        return if (result.isSuccessful()) {
            result.toSuccess().value.firstOrNull { it.type.equals(ImagesResolutionsEnum.BACKGROUND.name, true) }
        } else null
    }
}
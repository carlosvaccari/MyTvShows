package com.cvaccari.features.favorities.domain

import com.cvaccari.features.favorities.data.FavoritesRepository
import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

interface FavoritesUseCase {
    fun getFavorites(): Flow<List<ShowInfoModel>>
    suspend fun enrichWithFavorites(list: List<ShowInfoModel>): Flow<List<ShowInfoModel>>
    suspend fun handleFavorite(item: ShowInfoModel)
    fun getFavoriteById(id : Int): Flow<ShowInfoModel?>
}

class FavoritesUseCaseImpl(
    private val repository: FavoritesRepository
): FavoritesUseCase {

    override fun getFavorites(): Flow<List<ShowInfoModel>> {
        return repository.getFavorites()
    }

    override suspend fun enrichWithFavorites(showList: List<ShowInfoModel>): Flow<List<ShowInfoModel>> {
        val favoritesIdsMap = hashMapOf<String, ShowInfoModel>()

        repository.getFavorites().first().forEach {
            favoritesIdsMap[it.id] = it
        }

        showList.forEach {
            if(favoritesIdsMap.containsKey(it.id)) {
                it.isFavorite = true
            }
        }

        return flowOf(showList)
    }

    override suspend fun handleFavorite(item: ShowInfoModel) {
        repository.updateFavorite(item)
    }

    override fun getFavoriteById(id: Int): Flow<ShowInfoModel?> {
        return repository.getFavoriteById(id)
    }
}
package com.cvaccari.features.favorities.data

import com.cvaccari.features.search.data.model.ShowInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface FavoritesRepository {
    fun getFavorites(): Flow<List<ShowInfoModel>>
    suspend fun updateFavorite(item: ShowInfoModel)
    fun getFavoriteById(id: Int): Flow<ShowInfoModel?>
}

class FavoritesRepositoryImpl(
    private val localDataSource: FavoritesLocalDataSource
) : FavoritesRepository {

    override fun getFavorites(): Flow<List<ShowInfoModel>> {
        return localDataSource.getFavorities()
    }

    override suspend fun updateFavorite(item: ShowInfoModel) {
        getFavoriteById(item.id.toInt()).first()?.let {
            localDataSource.removeFavority(item)
        } ?: localDataSource.addFavority(item)
    }

    override fun getFavoriteById(id: Int): Flow<ShowInfoModel?> {
        return localDataSource.getFavoritesById(id)
    }

}
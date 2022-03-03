package com.cvaccari.features.favorities.data

import com.cvaccari.core_local_storage.database.dao.FavoritiesDao
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.data.model.toFavoritiesEntity
import com.cvaccari.features.search.data.model.toShowInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface FavoritesLocalDataSource {

    fun getFavorities(): Flow<List<ShowInfoModel>>

    fun getFavoritesById(id: Int) : Flow<ShowInfoModel?>

    suspend fun addFavority(item: ShowInfoModel)

    suspend fun removeFavority(item: ShowInfoModel)
}

class FavoritesLocalDataSourceImpl(
    private val favoritesDao: FavoritiesDao
): FavoritesLocalDataSource {

    override fun getFavorities(): Flow<List<ShowInfoModel>> {
        return favoritesDao.getFavorities().map { it.map { it.toShowInfoModel() } }
    }

    override suspend fun addFavority(item: ShowInfoModel){
        favoritesDao.insert(item.toFavoritiesEntity())
    }

    override suspend fun removeFavority(item: ShowInfoModel){
        favoritesDao.delete(item.id.toInt())
    }

    override fun getFavoritesById(id: Int): Flow<ShowInfoModel?> {
        return favoritesDao.getFavoriteById(id).map {
            it?.toShowInfoModel()
        }
    }
}

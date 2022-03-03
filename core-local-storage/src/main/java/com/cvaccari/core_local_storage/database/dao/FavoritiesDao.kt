package com.cvaccari.core_local_storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cvaccari.core_local_storage.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritiesDao {

    @Query("SELECT * FROM favoritesEntity ORDER BY LOWER(name) ASC")
    fun getFavorities(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favoritesEntity where id = :id")
    fun getFavoriteById(id: Int) : Flow<FavoritesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(entity: FavoritesEntity)

    @Query("DELETE FROM favoritesEntity WHERE id = :favoriteId")
    @JvmSuppressWildcards
    suspend fun delete(favoriteId: Int)

}
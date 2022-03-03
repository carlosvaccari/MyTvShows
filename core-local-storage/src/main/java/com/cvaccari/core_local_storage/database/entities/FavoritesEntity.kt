package com.cvaccari.core_local_storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritesEntity")
data class FavoritesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "genres") val genres: List<String>,
    @ColumnInfo(name = "imageMedium") val imageMedium: String? = null,
    @ColumnInfo(name = "imageOriginal") val imageOriginal: String? = null,
    @ColumnInfo(name = "summary") val summary: String
)
package com.cvaccari.core_local_storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cvaccari.core_local_storage.database.converters.FavoritesTypeConverters
import com.cvaccari.core_local_storage.database.dao.FavoritiesDao
import com.cvaccari.core_local_storage.database.entities.FavoritesEntity

@Database(
    entities = [FavoritesEntity::class],
    version = AppDatabase.VERSION,
    exportSchema = true
)
@TypeConverters(FavoritesTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritiesDao

    companion object {

        const val NAME = "series_database.db"
        const val VERSION = 1

        fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                .build()
    }

}

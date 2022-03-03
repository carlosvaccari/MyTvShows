package com.cvaccari.core_local_storage.database.di

import com.cvaccari.core_local_storage.database.AppDatabase
import com.cvaccari.core_local_storage.database.dao.FavoritiesDao
import org.koin.dsl.module

object LocalStorageModule {

    val instance = module {
        single {
            AppDatabase.buildDatabase(
                context = get()
            )
        }

        single {
            get<AppDatabase>().favoritesDao()
        }
    }
}
package com.cvaccari.features.favorities.di

import com.cvaccari.core_local_storage.database.AppDatabase
import com.cvaccari.features.favorities.data.FavoritesLocalDataSource
import com.cvaccari.features.favorities.data.FavoritesLocalDataSourceImpl
import com.cvaccari.features.favorities.data.FavoritesRepository
import com.cvaccari.features.favorities.data.FavoritesRepositoryImpl
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.favorities.domain.FavoritesUseCaseImpl
import com.cvaccari.features.favorities.presentation.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object FavoritesModule {

    val instance = module {
        viewModel {
            FavoritesViewModel(
                useCase = get()
            )
        }

        factory<FavoritesUseCase> {
            FavoritesUseCaseImpl(
                repository = get()
            )
        }

        factory<FavoritesRepository> {
            FavoritesRepositoryImpl(
                localDataSource = get()
            )
        }

        factory<FavoritesLocalDataSource> {
            FavoritesLocalDataSourceImpl(
                favoritesDao = get()
            )
        }
    }
}
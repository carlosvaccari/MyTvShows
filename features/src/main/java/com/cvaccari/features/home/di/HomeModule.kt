package com.cvaccari.features.home.di

import com.cvaccari.features.home.data.HomeApi
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.home.data.HomeRepositoryImpl
import com.cvaccari.features.home.domain.HomeUseCase
import com.cvaccari.features.home.domain.HomeUseCaseImpl
import com.cvaccari.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {

    val instance = module {

        viewModel {
            HomeViewModel(
                useCase = get(),
                favoritesUseCase = get()
            )
        }

        factory<HomeUseCase> {
            HomeUseCaseImpl(
                repository = get()
            )
        }

        factory<HomeRepository> {
            HomeRepositoryImpl(
                api = get()
            )
        }

        factory { providesRetrofit(get()) }
    }


    private fun providesRetrofit(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)
}

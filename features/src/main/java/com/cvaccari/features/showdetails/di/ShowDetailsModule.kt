package com.cvaccari.features.showdetails.di

import com.cvaccari.features.search.data.SearchApi
import com.cvaccari.features.search.data.SearchRepository
import com.cvaccari.features.search.data.SearchRepositoryImpl
import com.cvaccari.features.search.domain.SearchUseCase
import com.cvaccari.features.search.domain.SearchUseCaseImpl
import com.cvaccari.features.search.presentation.SearchViewModel
import com.cvaccari.features.showdetails.data.ShowDetailsApi
import com.cvaccari.features.showdetails.data.ShowDetailsRepository
import com.cvaccari.features.showdetails.data.ShowDetailsRepositoryImpl
import com.cvaccari.features.showdetails.domain.ShowDetailsUseCase
import com.cvaccari.features.showdetails.domain.ShowDetailsUseCaseImpl
import com.cvaccari.features.showdetails.presentation.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ShowDetailsModule  {

    val instance = module {
        viewModel { (showId: String) ->
            ShowDetailsViewModel(
                showId = showId,
                useCase = get()
            )
        }

        factory<ShowDetailsUseCase> {
            ShowDetailsUseCaseImpl(
                repository = get()
            )
        }

        factory<ShowDetailsRepository> {
            ShowDetailsRepositoryImpl(
                api = get()
            )
        }

        factory { providesRetrofit(get()) }
    }

    private fun providesRetrofit(retrofit: Retrofit): ShowDetailsApi =
        retrofit.create(ShowDetailsApi::class.java)
}

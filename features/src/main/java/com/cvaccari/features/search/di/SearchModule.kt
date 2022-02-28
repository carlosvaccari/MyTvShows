package com.cvaccari.features.search.di

import com.cvaccari.features.search.data.SearchApi
import com.cvaccari.features.search.data.SearchRepository
import com.cvaccari.features.search.data.SearchRepositoryImpl
import com.cvaccari.features.search.domain.SearchUseCase
import com.cvaccari.features.search.domain.SearchUseCaseImpl
import com.cvaccari.features.search.presentation.SearchViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel

object SearchModule {

    val instance = module {

        viewModel {
            SearchViewModel(
                useCase = get()
            )
        }

        factory<SearchUseCase> {
            SearchUseCaseImpl(
                repository = get()
            )
        }

        factory<SearchRepository> {
            SearchRepositoryImpl(
                api = get()
            )
        }

        factory { providesRetrofit(get()) }
    }


    private fun providesRetrofit(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)
}

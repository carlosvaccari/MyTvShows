package com.cvaccari.features.episodedetails.di

import com.cvaccari.features.episodedetails.data.EpisodeDetailsApi
import com.cvaccari.features.episodedetails.data.EpisodeDetailsRepository
import com.cvaccari.features.episodedetails.data.EpisodeDetailsRepositoryImpl
import com.cvaccari.features.episodedetails.domain.EpisodeDetailsUseCase
import com.cvaccari.features.episodedetails.domain.EpisodeDetailsUseCaseImpl
import com.cvaccari.features.episodedetails.presentation.EpisodeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object EpisodeDetailsModule {

    val instance = module {
        viewModel { (showId: String, season: String, number: String) ->
            EpisodeDetailsViewModel(
                useCase = get(),
                showId = showId,
                season = season,
                number = number
            )
        }

        factory<EpisodeDetailsUseCase> {
            EpisodeDetailsUseCaseImpl(
                repository = get()
            )
        }

        factory<EpisodeDetailsRepository> {
            EpisodeDetailsRepositoryImpl(
                api = get()
            )
        }

        factory { providesRetrofit(get()) }
    }


    private fun providesRetrofit(retrofit: Retrofit): EpisodeDetailsApi =
        retrofit.create(EpisodeDetailsApi::class.java)
}
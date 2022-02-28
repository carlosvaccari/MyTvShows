package com.cvaccari.core_network.di

import com.cvaccari.core_network.BuildConfig
import com.cvaccari.core_network.factory.NetworkReponseCallFactory
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val instance = module {

        single {
            Gson()
        }

        single<GsonConverterFactory> {
            GsonConverterFactory.create(get())
        }

        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(get())
                .addConverterFactory(get<GsonConverterFactory>())
                .addCallAdapterFactory(NetworkReponseCallFactory.create())
                .build()
        }

        single {
            OkHttpClient.Builder().apply {
                addInterceptor(get<HttpLoggingInterceptor>())
            }.build()
        }

        single {
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}

package com.cvaccari.features.commons

import android.app.Application
import com.cvaccari.core_local_storage.database.di.LocalStorageModule
import com.cvaccari.core_network.di.NetworkModule
import com.cvaccari.features.favorities.di.FavoritesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class RobolectricTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RobolectricTestApplication)
        }

        loadKoinModules(
            listOf(
                NetworkModule.instance,
                LocalStorageModule.instance,
                FavoritesModule.instance
            )
        )
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}
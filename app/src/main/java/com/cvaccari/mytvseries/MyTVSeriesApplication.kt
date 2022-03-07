package com.cvaccari.mytvseries

import android.app.Application
import com.cvaccari.commons.alarm.AlarmUtils
import com.cvaccari.core_local_storage.database.di.LocalStorageModule
import com.cvaccari.core_network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level

class MyTVSeriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupReminder()
    }

    private fun setupReminder() {
        AlarmUtils.setupReminder(this@MyTVSeriesApplication)
    }

    private fun setupKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR) else EmptyLogger()
            androidContext(this@MyTVSeriesApplication)
            modules(
                NetworkModule.instance,
                LocalStorageModule.instance
            )
        }
    }

}
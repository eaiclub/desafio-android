package com.example.desafio_android

import android.app.Application
import com.example.desafio_android.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PusherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@PusherApplication)
            modules(modulesList)
        }
    }
}
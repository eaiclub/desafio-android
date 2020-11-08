package com.example.iterativechallenger.core

import android.app.Application
import com.example.iterativechallenger.core.utils.modules
import com.example.iterativechallenger.core.utils.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    val myModules = listOf(modules, viewModelModule)


    companion object{
        lateinit var instance : App
    }

    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}
package com.lucasdonato.nasa.presentation

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.lucasdonato.nasa.mechanism.dependencies.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class AppApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        // Hold application context reference
        val context = applicationContext
        sContext = WeakReference(context)
        setupMultiDex()
        setupKoin()
        setupStetho()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupMultiDex() {
        MultiDex.install(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@AppApplication)
            modules(applicationModules)
        }
    }

    companion object {

        private val TAG = AppApplication::class.java.simpleName

        private var sContext: WeakReference<Context>? = null

        val context: Context?
            get() = sContext?.get()
    }
}

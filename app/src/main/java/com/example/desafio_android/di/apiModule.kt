package com.example.desafio_android.di

import com.example.desafio_android.util.ResponseHandler
import com.example.desafio_android.data.remote.provideNasaApi
import com.example.desafio_android.data.remote.provideRetrofit
import org.koin.dsl.module

val apiModule = module {
    factory { ResponseHandler() }
    factory { provideRetrofit() }
    factory { provideNasaApi(get()) }
}

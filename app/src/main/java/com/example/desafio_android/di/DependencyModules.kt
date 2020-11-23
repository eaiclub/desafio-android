package com.example.desafio_android.di

import com.example.desafio_android.common.ResponseHandler
import com.example.desafio_android.common.domain.models.NasaApodResponse
import com.example.desafio_android.common.domain.repository.NasaRepository
import com.example.desafio_android.data.remote.provideNasaApi
import com.example.desafio_android.data.remote.provideRetrofit
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule = module {
    factory { ResponseHandler() }
    factory { provideRetrofit() }
    factory { provideNasaApi(get()) }
}
lateinit var viewModelModule: Module
lateinit var useCaseModule: Module
val repositoryModule = module {
    factory { NasaRepository(get(), get()) }
}

val modulesList = listOf(
    apiModule,
//    viewModelModule,
//    useCaseModule,
    repositoryModule
)
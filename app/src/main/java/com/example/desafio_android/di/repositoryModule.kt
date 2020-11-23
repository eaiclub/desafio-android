package com.example.desafio_android.di

import com.example.desafio_android.common.domain.repository.NasaRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { NasaRepository(get(), get()) }
}

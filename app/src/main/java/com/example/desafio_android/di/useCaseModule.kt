package com.example.desafio_android.di

import com.example.desafio_android.common.domain.usecase.ApodUseCase
import org.koin.dsl.module

val useCaseModule = module {
 factory { ApodUseCase(get())}
}

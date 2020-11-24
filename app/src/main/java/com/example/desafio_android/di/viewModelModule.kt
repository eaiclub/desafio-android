package com.example.desafio_android.di

import com.example.desafio_android.ui.NasaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NasaViewModel(get()) }
}

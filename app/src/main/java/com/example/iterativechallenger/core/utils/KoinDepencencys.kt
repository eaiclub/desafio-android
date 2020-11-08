package com.example.iterativechallenger.core.utils

import com.example.iterativechallenger.data.api.ApiService
import com.example.iterativechallenger.data.api.RestApi
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSource
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSourceImpl
import com.example.iterativechallenger.data.mappers.ApodDataResponseToApodMapper
import com.example.iterativechallenger.data.repositories.ApodRepositoryImpl
import com.example.iterativechallenger.domain.repositories.ApodRepository
import com.example.iterativechallenger.domain.usecases.GetListApod
import com.example.iterativechallenger.presentation.pages.apods.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }
    single<ApodRemoteDataSource> { ApodRemoteDataSourceImpl(get())}

    single{ ApodDataResponseToApodMapper()}
    single<ApodRepository> { ApodRepositoryImpl(get(), get())}
    single{ ApodRepositoryImpl(get(), get())}

    single{ GetListApod(get())}

}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
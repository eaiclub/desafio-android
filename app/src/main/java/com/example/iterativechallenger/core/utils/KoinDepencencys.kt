package com.example.iterativechallenger.core.utils

import com.example.iterativechallenger.data.api.ApiService
import com.example.iterativechallenger.data.api.RestApi
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSource
import com.example.iterativechallenger.data.data_sources.ApodRemoteDataSourceImpl
import org.koin.dsl.module

val modules = module {
    single<ApiService> { RestApi.getRetrofit().create(ApiService::class.java) }

    single<ApodRemoteDataSource> { ApodRemoteDataSourceImpl(get())}
}
package com.example.nasapicturesapp.repository

import com.example.nasapicturesapp.data_remote.WebService
import com.example.nasapicturesapp.domain.repository.PictureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(webService: WebService): PictureRepository =
        PictureRepositoryImpl(webService)
}
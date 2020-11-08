package com.example.infinitescroll.di

import com.example.infinitescroll.data.api.ApodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class EndPointModule {

    @Singleton
    @Provides
    fun provideApodService(): ApodService {
        return ApodService.create()
    }

}
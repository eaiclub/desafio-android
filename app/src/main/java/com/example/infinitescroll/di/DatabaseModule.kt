package com.example.infinitescroll.di

import android.content.Context
import com.example.infinitescroll.data.ApodDao
import com.example.infinitescroll.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideApodDao(appDatabase: AppDatabase): ApodDao {
        return appDatabase.apodDao()
    }

}
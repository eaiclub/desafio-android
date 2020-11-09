package com.example.infinitescroll.domain.repository

import androidx.paging.DataSource
import com.example.infinitescroll.data.api.Resource
import com.example.infinitescroll.data.model.Apod

interface ApodRepository {

    fun getApod() : DataSource.Factory<Int, Apod>

    suspend fun loadApod(date : String) : Apod

    suspend fun loadApodRange(startDate : String, endDate : String) : Resource<List<Apod>>

    suspend fun getLastApod() : Apod?

}
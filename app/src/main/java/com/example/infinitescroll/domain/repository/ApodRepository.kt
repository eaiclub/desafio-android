package com.example.infinitescroll.domain.repository

import androidx.paging.PagingData
import com.example.infinitescroll.data.model.Apod
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ApodRepository {

    fun getApod(pageSize : Int = 30) : Flow<PagingData<Apod>>

    suspend fun loadApod(date : String) : Apod

    suspend fun loadApodRange(startDate : String, endDate : String) : List<Apod>

}
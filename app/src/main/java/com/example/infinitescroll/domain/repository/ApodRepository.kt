package com.example.infinitescroll.domain.repository

import com.example.infinitescroll.data.model.Apod

interface ApodRepository {

    suspend fun getApod(pageSize : Int, pageIndex : Int) : List<Apod>

    suspend fun loadApod(date : String) : Apod

    suspend fun loadApodRange(startDate : String, endDate : String) : List<Apod>

    suspend fun getLastApod() : Apod?

}
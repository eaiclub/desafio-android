package com.example.nasapicturesapp.repository

import com.example.nasapicturesapp.data_remote.WebService
import com.example.nasapicturesapp.domain.model.Picture
import com.example.nasapicturesapp.domain.repository.PictureRepository
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val webService: WebService,
): PictureRepository {
    override suspend fun getPictures(startDate: String, endDate: String): List<Picture> {
        return webService.getApod("DEMO_KEY", startDate, endDate)
    }
}
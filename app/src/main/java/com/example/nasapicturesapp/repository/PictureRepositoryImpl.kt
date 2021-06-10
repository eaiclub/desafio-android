package com.example.nasapicturesapp.repository

import com.example.nasapicturesapp.data_remote.Constants
import com.example.nasapicturesapp.data_remote.WebService
import com.example.nasapicturesapp.data_remote.mappers.PictureMappers.toModel
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.domain.repository.PictureRepository
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val webService: WebService,
): PictureRepository {
    override suspend fun getPictures(startDate: String, endDate: String): List<PictureModel> {
        return webService.getApod(Constants.Api.demoKey, startDate, endDate).toModel()
    }

    override suspend fun getPicture(date: String): PictureModel {
        return webService.getPicture(Constants.Api.demoKey, date).toModel()
    }
}
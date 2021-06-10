package com.example.nasapicturesapp.domain.repository

import com.example.nasapicturesapp.domain.model.PictureModel

interface PictureRepository {
    suspend fun getPictures(startDate: String, endDate: String): List<PictureModel>
}
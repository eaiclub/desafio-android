package com.example.nasapicturesapp.domain.model

data class PictureModel(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdUrl: String,
    val mediaType: String,
    val title: String,
    val url: String
)
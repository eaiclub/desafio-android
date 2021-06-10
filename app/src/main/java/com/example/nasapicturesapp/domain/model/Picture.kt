package com.example.nasapicturesapp.domain.model

import com.google.gson.annotations.SerializedName

//TODO: Create PictureResponse and mapper
data class Picture(
    @SerializedName("hdurl")
    var url: String,
    @SerializedName("title")
    val title: String
)
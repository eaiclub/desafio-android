package com.example.infinitescroll.data.response

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    val copyright : String?,
    val date : String?,
    val explanation : String?,
    @SerializedName("hdurl") val hdUrl : String?,
    @SerializedName("media_type") val mediaType : String?,
    @SerializedName("service_version") val serviceVersion : String?,
    val title : String?,
    val url : String?
)
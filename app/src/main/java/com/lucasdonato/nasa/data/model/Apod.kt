package com.lucasdonato.nasa.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Apod(
    @SerializedName("date") val date : String,
    @SerializedName("explanation") val explanation : String,
    @SerializedName("hdurl") val hdUrl : String,
    @SerializedName("media_type") val mediaType : String,
    @SerializedName("service_version") val serviceVersion : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String,
    @SerializedName("copyright") val copyright : String?
) : Serializable


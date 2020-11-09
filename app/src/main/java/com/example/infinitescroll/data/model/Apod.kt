package com.example.infinitescroll.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "apods")
data class Apod(
    val copyright : String,
    @PrimaryKey val date : Calendar,
    val explanation : String,
    val hdUrl : String,
    val mediaType : String,
    val serviceVersion : String,
    val title : String,
    val url : String
) : Serializable {

    fun getFormattedDate(): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date.time)

    fun hasImage() = mediaType == "image"

}
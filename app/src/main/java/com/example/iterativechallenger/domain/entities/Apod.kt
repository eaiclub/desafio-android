package com.example.iterativechallenger.domain.entities

import java.io.Serializable
import java.util.*


data class Apod(
    val title : String,
    val copyright : String,
    val date : Date,
    val explanation : String,
    val url : String,
    val hdUrl : String,
    val mediaType : String
) : Serializable
package com.example.desafio_android.util

import com.example.desafio_android.common.domain.models.NasaApod

interface IOnClickListener {
    fun onClick(apod: NasaApod)
}
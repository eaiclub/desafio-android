package com.example.desafio_android

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}
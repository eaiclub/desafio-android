package com.jess.eaiclubnasa.viewmodel.event

sealed class ApodEvent {
    data class Loading(val status: Boolean) : ApodEvent()
}

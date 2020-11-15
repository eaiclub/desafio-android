package com.jess.eaiclubnasa.viewmodel.interactor

sealed class ApodInteractor {
    data class GetListApod(val date: String): ApodInteractor()
}
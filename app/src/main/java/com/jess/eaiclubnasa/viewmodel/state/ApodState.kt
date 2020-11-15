package com.jess.eaiclubnasa.viewmodel.state

import com.jess.eaiclubnasa.model.ApodResult

sealed class ApodState {
    data class ApodListSuccess(val list: MutableList<ApodResult>) : ApodState()
    data class ApodListError(val messageError: String) : ApodState()
}
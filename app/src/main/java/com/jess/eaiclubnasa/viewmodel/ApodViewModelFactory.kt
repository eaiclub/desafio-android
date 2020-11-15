package com.jess.eaiclubnasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jess.eaiclubnasa.repository.ApodRepository
import kotlinx.coroutines.CoroutineDispatcher

class ApodViewModelFactory(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: ApodRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            CoroutineDispatcher::class.java,
            ApodRepository::class.java
        ).newInstance(ioDispatcher, repository)
    }
}
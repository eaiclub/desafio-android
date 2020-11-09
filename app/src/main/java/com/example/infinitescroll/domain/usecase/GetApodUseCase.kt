package com.example.infinitescroll.domain.usecase

import androidx.paging.DataSource
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.repository.ApodRepository
import javax.inject.Inject

/**
 * fetch apods locally.
 */
class GetApodUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    fun execute() : DataSource.Factory<Int, Apod> {
        return apodRepository.getApod()
    }

}
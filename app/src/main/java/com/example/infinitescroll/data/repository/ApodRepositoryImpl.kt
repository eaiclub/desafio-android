package com.example.infinitescroll.data.repository

import androidx.paging.DataSource
import com.example.infinitescroll.data.api.ApodService
import com.example.infinitescroll.data.api.Resource
import com.example.infinitescroll.data.api.ResponseHandler
import com.example.infinitescroll.data.local.ApodDao
import com.example.infinitescroll.data.mapper.ApodMapper
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.data.response.ApodResponse
import com.example.infinitescroll.domain.repository.ApodRepository
import javax.inject.Inject

/**
 * Implementation of ApodRepository
 * @see com.example.infinitescroll.domain.repository.ApodRepository
 */
class ApodRepositoryImpl @Inject constructor(
        private val apodDao: ApodDao,
        private val apodService: ApodService,
        private val apodMapper: ApodMapper,
        private val responseHandler: ResponseHandler
): ApodRepository {

    override fun getApod() : DataSource.Factory<Int, Apod> {
        return apodDao.getApods()
    }

    override suspend fun loadApod(date : String) : Apod {
        val apodResponse = apodService.getApod(date)
        val apod = apodMapper.map(apodResponse)
        apodDao.insertApod(apod)
        return apod
    }

    override suspend fun loadApodRange(startDate : String, endDate : String) : Resource<List<Apod>> {
        val apodResponseList : List<ApodResponse>
        try {
            apodResponseList = apodService.getApodRange(startDate, endDate)
        } catch (e: Exception) {
            return responseHandler.handleException(e)
        }
        val apodList = apodMapper.map(apodResponseList)
        apodDao.insertApodList(apodList)
        return responseHandler.handleSuccess(apodList)
    }

    override suspend fun getLastApod(): Apod? {
        return apodDao.getLastApod()
    }
}

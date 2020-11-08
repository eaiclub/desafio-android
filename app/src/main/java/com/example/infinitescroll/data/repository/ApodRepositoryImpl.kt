package com.example.infinitescroll.data.repository

import com.example.infinitescroll.data.api.ApodService
import com.example.infinitescroll.data.local.ApodDao
import com.example.infinitescroll.data.mapper.ApodMapper
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.repository.ApodRepository
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
        private val apodDao: ApodDao,
        private val apodService: ApodService,
        private val apodMapper: ApodMapper
): ApodRepository {

    override suspend fun getApod(pageSize : Int, pageIndex : Int) : List<Apod> {
        return apodDao.getApods(pageSize, pageIndex)
    }

    override suspend fun loadApod(date : String) : Apod {
        val apodResponse = apodService.getApod(date)
        val apod = apodMapper.map(apodResponse)
        apodDao.insertApod(apod)
        return apod
    }

    override suspend fun loadApodRange(startDate : String, endDate : String) : List<Apod> {
        val apodResponseList = apodService.getApodRange(startDate, endDate)
        val apodList = apodMapper.map(apodResponseList)
        apodList.forEach {
            apodDao.insertApod(it)
        }
        return apodList
    }

    override suspend fun getLastApod(): Apod? {
        return apodDao.getLastApod()
    }
}

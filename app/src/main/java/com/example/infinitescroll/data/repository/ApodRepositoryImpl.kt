package com.example.infinitescroll.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infinitescroll.data.ApodPagingSource
import com.example.infinitescroll.data.api.ApodService
import com.example.infinitescroll.data.local.ApodDao
import com.example.infinitescroll.data.mapper.ApodMapper
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
        private val apodDao: ApodDao,
        private val apodService: ApodService,
        private val apodMapper: ApodMapper
): ApodRepository {

    override fun getApod(pageSize : Int) : Flow<PagingData<Apod>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = pageSize, initialLoadSize = pageSize),
            pagingSourceFactory = { ApodPagingSource(apodDao) }
        ).flow
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

}

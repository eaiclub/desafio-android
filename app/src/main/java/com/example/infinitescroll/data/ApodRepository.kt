package com.example.infinitescroll.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infinitescroll.api.ApodService
import com.example.infinitescroll.mapper.ApodMapper
import com.example.infinitescroll.model.Apod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodDao: ApodDao,
    private val apodService: ApodService,
    private val apodMapper: ApodMapper
) {

    fun getApod(pageSize : Int = 30) : Flow<PagingData<Apod>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = pageSize),
            pagingSourceFactory = { ApodPagingSource(apodDao) }
        ).flow
    }

    suspend fun loadApod(date : String) : Apod {
        val apodResponse = apodService.getApod(date)
        val apod = apodMapper.map(apodResponse)
        apodDao.insertApod(apod)
        return apod
    }

}

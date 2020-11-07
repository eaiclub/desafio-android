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

    companion object {
        private const val PAGE_SIZE = 30
    }

    fun getApod(pageSize : Int = 30, pageIndex : Int = 1) : Flow<PagingData<Apod>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
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

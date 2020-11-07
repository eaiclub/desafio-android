package com.example.infinitescroll.data

import androidx.lifecycle.LifecycleOwner
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infinitescroll.api.ApodService
import com.example.infinitescroll.mapper.ApodMapper
import com.example.infinitescroll.model.Apod
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ApodRepository @Inject constructor(
    private val apodDao: ApodDao,
    private val apodService: ApodService,
    private val apodMapper: ApodMapper
) {

    fun getApod(pageSize : Int = 30) : Flow<PagingData<Apod>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = pageSize, initialLoadSize = pageSize),
            pagingSourceFactory = { ApodPagingSource(apodDao) }
        ).flow
    }

    suspend fun loadApod(date : String) : Apod {
        val apodResponse = apodService.getApod(date)
        val apod = apodMapper.map(apodResponse)
        apodDao.insertApod(apod)
        return apod
    }

    suspend fun loadApodRange(date : Date, backwardRange : Int) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        coroutineScope {
            val deferred = arrayListOf<Deferred<Apod>>()
            for(i in 0..backwardRange){
                calendar.add(Calendar.DATE, -1)
                val dateStr = sdf.format(calendar.time)
                deferred.add(async { loadApod(dateStr) })
            }
            deferred.awaitAll()
        }
    }

}

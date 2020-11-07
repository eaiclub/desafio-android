package com.example.infinitescroll.data

import androidx.paging.PagingSource
import com.example.infinitescroll.model.Apod

class ApodPagingSource(
    private val apodDao: ApodDao
) : PagingSource<Int, Apod>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Apod> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val apods = apodDao.getApods(params.loadSize, pageIndex)
            LoadResult.Page(
                data = apods,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = pageIndex + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}

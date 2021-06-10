package com.example.nasapicturesapp.ui.main.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.domain.repository.PictureRepository
import com.example.nasapicturesapp.util.lessTenDays
import com.example.nasapicturesapp.util.moreTenDays
import javax.inject.Inject

class PicturesPagingSource @Inject constructor(private val pictureRepository: PictureRepository) :
    PagingSource<PageInfo, PictureModel>() {

    override suspend fun load(params: LoadParams<PageInfo>): LoadResult<PageInfo, PictureModel> {
        return try {
            val startDate = params.key?.startDate ?: "2021-06-05"
            val endDate = params.key?.endDate ?: "2021-06-09"
            val response = pictureRepository.getPictures(startDate, endDate)
            LoadResult.Page(
                data = response,
                prevKey = PageInfo(startDate.moreTenDays(), endDate.moreTenDays()),
                nextKey = PageInfo(startDate.lessTenDays(), endDate.lessTenDays())
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<PageInfo, PictureModel>): PageInfo? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plusOne() ?: anchorPage?.nextKey?.minusOne()
        }
    }

}
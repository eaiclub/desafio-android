package com.example.nasapicturesapp.ui.main.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.domain.model.PictureModel.Companion.imageType
import com.example.nasapicturesapp.domain.repository.PictureRepository
import com.example.nasapicturesapp.ui.main.pagination.PageInfo.Companion.perPage
import com.example.nasapicturesapp.util.DateUtil
import com.example.nasapicturesapp.util.hasNextDay
import com.example.nasapicturesapp.util.nextDays
import com.example.nasapicturesapp.util.previewDays
import javax.inject.Inject

class PicturesPagingSource @Inject constructor(private val pictureRepository: PictureRepository) :
    PagingSource<PageInfo, PictureModel>() {

    override suspend fun load(params: LoadParams<PageInfo>): LoadResult<PageInfo, PictureModel> {
        return try {
            val today = DateUtil.getToday()
            val startDate = params.key?.startDate ?: today.previewDays(perPage)
            val endDate = params.key?.endDate ?: today
            // The videos won't be showed because their links are from youtube
            val response = pictureRepository.getPictures(startDate, endDate).filter {
                it.mediaType == imageType
            }
            LoadResult.Page(
                data = response,
                prevKey = if(endDate.hasNextDay()) PageInfo(startDate.nextDays(perPage), endDate.nextDays(perPage)) else null,
                nextKey = PageInfo(startDate.previewDays(perPage), endDate.previewDays(perPage))
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
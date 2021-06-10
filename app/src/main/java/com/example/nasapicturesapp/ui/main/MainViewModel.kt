package com.example.nasapicturesapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nasapicturesapp.domain.model.Picture
import com.example.nasapicturesapp.ui.main.pagination.PicturesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    picturesPagingSource: PicturesPagingSource
) : ViewModel() {

    val pictures: Flow<PagingData<Picture>> = Pager(PagingConfig(pageSize =  10)) {
        picturesPagingSource
    }.flow
        .cachedIn(viewModelScope)
}
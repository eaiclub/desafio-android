package com.example.nasapicturesapp.ui.picture_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.domain.repository.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class PictureDetailViewModel @Inject constructor(
    private val repository: PictureRepository,
) : ViewModel() {

    val picture = MutableLiveData<PictureModel>()

    fun initViewModel(date: String) {
        viewModelScope.launch {
            try {
                val data = repository.getPicture(date)
                picture.postValue(data)
            } catch (e: HttpException) {
                //  handle error
            }
        }
    }
}

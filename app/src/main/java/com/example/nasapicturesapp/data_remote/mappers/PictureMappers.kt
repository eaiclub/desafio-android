package com.example.nasapicturesapp.data_remote.mappers

import com.example.nasapicturesapp.data_remote.response.PictureResponse
import com.example.nasapicturesapp.domain.model.PictureModel

object PictureMappers {
    fun PictureResponse.toModel() = PictureModel(
        copyright= this.copyright.orEmpty(),
        date = this.date.orEmpty(),
        explanation = this.explanation.orEmpty(),
        hdUrl = this.hdUrl.orEmpty(),
        mediaType = this.mediaType.orEmpty(),
        title = this.title.orEmpty(),
        url = this.url.orEmpty()
    )

    fun List<PictureResponse>.toModel(): List<PictureModel> = this.map {
        it.toModel()
    }
}
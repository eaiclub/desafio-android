package com.example.desafio_android.util

import android.os.Build
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.common.domain.models.NasaApodResponse
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun List<NasaApodResponse>.mapForView(): List<NasaApod>{
    val apodList =  this.map {
        NasaApod(
            it.copyright ?: "",
            it.date ?: "",
            it.explanation ?: "",
            it.hdurl ?: "",
            it.media_type ?: "",
            it.service_version ?: "",
            it.title ?: "",
            it.url ?: ""
        )
    }
    val sortedList = apodList.sortedByDescending { it.date }
    return sortedList
}

fun Calendar.toStringDate(): String{
    return SimpleDateFormat(DATE_YEAR_MONTH_DAY, Locale.US).format(this.time)
}


@BindingAdapter("imageUrl")
fun ImageView.load(imagemUrl: String?) {
    val imageView = this
    val url = imagemUrl
    val requestOptions = Glide.with(imageView.context).load(url).apply(
        RequestOptions().optionalFitCenter().centerInside().centerCrop()
    ).transition(DrawableTransitionOptions.withCrossFade(300))
    requestOptions.into(this)
}

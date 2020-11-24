package com.example.desafio_android.util

import android.os.Build
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.desafio_android.R
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
    if (imagemUrl.toString().contains(".jpg")){
        val requestOptions = Glide.with(imageView.context).load(imagemUrl).apply(
                RequestOptions().optionalFitCenter().centerInside().centerCrop()
        ).transition(DrawableTransitionOptions.withCrossFade(300))
        requestOptions.into(this)
    }else{
       val image = imageView.context.resources.getDrawable(R.drawable.errorimage)
        Glide.with(imageView).load(image).into(this)
    }

}

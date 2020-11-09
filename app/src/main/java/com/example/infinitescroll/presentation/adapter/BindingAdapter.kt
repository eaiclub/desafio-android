package com.example.infinitescroll.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * bindings for xml use.
 */
class BindingAdapter {

    companion object {

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if(imageUrl.isNullOrBlank())
                return
            Glide.with(view)
                .load(imageUrl)
                .into(view)
        }
    }


}
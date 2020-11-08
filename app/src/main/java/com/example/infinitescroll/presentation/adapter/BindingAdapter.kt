package com.example.infinitescroll.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingAdapter {

    companion object {

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if(imageUrl.isNullOrBlank())
                return
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }


}
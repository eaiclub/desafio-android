package com.example.nasapicturesapp.util

import android.widget.ImageView
import com.example.nasapicturesapp.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_round_image_24)
        .into(this)
}

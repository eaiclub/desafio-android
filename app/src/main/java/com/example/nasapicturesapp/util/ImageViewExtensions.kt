package com.example.nasapicturesapp.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}

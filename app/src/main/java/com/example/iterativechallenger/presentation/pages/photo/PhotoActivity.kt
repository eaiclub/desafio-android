package com.example.iterativechallenger.presentation.pages.photo

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.iterativechallenger.R
import com.example.iterativechallenger.domain.entities.Apod
import kotlinx.android.synthetic.main.activity_photo.*


class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val apod = intent.getSerializableExtra("apod") as Apod
        Glide.with(this).load(apod.url).into(photo_view)
        tv_explanation.text = apod.explanation

        photo_view.setOnClickListener {
            viewFullScreenPhoto(apod)
        }
        Toast.makeText(this, "aaaa", Toast.LENGTH_SHORT).show()
    }

    private fun viewFullScreenPhoto(apod : Apod){

        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_foto)
        val photo = dialog.findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(apod.url).into(photo)
        dialog.show()
    }


}
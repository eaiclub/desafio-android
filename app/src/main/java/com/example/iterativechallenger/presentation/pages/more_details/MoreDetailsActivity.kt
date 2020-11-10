package com.example.iterativechallenger.presentation.pages.more_details

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.iterativechallenger.BuildConfig
import com.example.iterativechallenger.R
import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.domain.entities.Apod
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_more_details.*
import kotlinx.android.synthetic.main.dialog_foto.*
import java.text.SimpleDateFormat
import java.util.*


class MoreDetailsActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)

        val apod = intent.getSerializableExtra("apod") as Apod
        val sdf = SimpleDateFormat(Constantes.FORMATO_DIA_MES_ANO, Locale.US)
        Glide.with(this).load(apod.url).into(photo_view)
        tv_explanation.text = apod.explanation
        if(apod.copyright.isNotEmpty())
            tv_copyright.text = "copyright: ${apod.copyright}"
        else
            tv_copyright.visibility = View.GONE
        
        tv_date.text = sdf.format(apod.date)


        photo_view.setOnClickListener {
            if(apod.mediaType == Constantes.TIPO_IMAGE)
                viewFullScreenPhoto(apod)
            else if(apod.mediaType == Constantes.TIPO_VIDEO)
                showVideo(apod)
        }
    }

    private fun showVideo(apod: Apod){

        photo_view.visibility = View.INVISIBLE
        youtube_video.visibility = View.VISIBLE

        youtube_video.initialize(
            BuildConfig.YOUTUBE_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
//                    remove a parte nao util do link pro player
                    p1?.loadVideo(apod.url.removePrefix("https://www.youtube.com/embed/").removeSuffix("?rel=0"))
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(applicationContext, Constantes.ERRO_YOUTUBE, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun viewFullScreenPhoto(apod: Apod){

        val dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_foto)
        val photo = dialog.findViewById<ImageView>(R.id.image_view)
        Glide.with(this).load(apod.url).into(photo)
        dialog.show()
    }


}
package com.lucasdonato.nasa.presentation.detail.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.mechanism.EXTRA_APOD
import com.lucasdonato.nasa.presentation.AppApplication.Companion.context
import com.lucasdonato.nasa.presentation.home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_onboarding.*
import kotlinx.android.synthetic.main.include_card_image_description.*
import kotlinx.android.synthetic.main.include_description.*
import kotlinx.android.synthetic.main.view_apod_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, apod: Apod? = null) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_APOD, apod)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        receiveAndSetDate()
        setupListeners()
    }

    private fun setupListeners() {
        back.setOnClickListener { finish() }
    }

    private fun receiveAndSetDate() {
        val apod = intent?.getSerializableExtra(EXTRA_APOD) as Apod?

        apod.also {
            it?.url.let { photoUrl ->
                Glide.with(this).load(photoUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?, model: Any?,
                            target: Target<Drawable>?, isFirstResource: Boolean
                        ): Boolean {
                            image_apod_description.setImageResource(R.drawable.ic_astronaut_empty)
                            image_progress.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?, model: Any?,
                            target: Target<Drawable>?, dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            image_progress.visibility = View.GONE
                            return false
                        }
                    }).into(image_apod_description)
            }

            date_description.text = it?.date
            title_description.text = it?.title
            message_description.text = it?.explanation
        }
    }

}
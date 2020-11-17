package com.lucasdonato.nasa.presentation.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.data.model.Apod
import com.lucasdonato.nasa.presentation.base.adapter.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.view_apod_list.view.*

class ApodRecyclerAdapter : BaseRecyclerAdapter<Apod, ApodRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mData[position], position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            (R.layout.view_apod_list), viewGroup,
            false
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(apod: Apod, position: Int) {
            itemView.apply {

                container.setOnClickListener {
                    onItemClickListener?.invoke(apod)
                }

                apod.copyright.let {
                    if (it.isNullOrEmpty()) itemView.copyright.text =
                        context.getString(R.string.without_copyright)
                    else itemView.copyright.text = apod.copyright
                }

                apod.url.let { photoUrl ->
                    Glide.with(context).load(photoUrl)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?, model: Any?,
                                target: Target<Drawable>?, isFirstResource: Boolean
                            ): Boolean {
                                itemView.image_apod.setImageResource(R.drawable.ic_astronaut_empty)
                                itemView.image_progress.visibility = GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?, model: Any?,
                                target: Target<Drawable>?, dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                itemView.image_progress.visibility = GONE
                                return false
                            }
                        }).into(image_apod)
                }

                container.background =
                    ContextCompat.getDrawable(context, R.drawable.shape_imagem_apod_card)
            }
        }

    }

    override fun validateDate() = false

}
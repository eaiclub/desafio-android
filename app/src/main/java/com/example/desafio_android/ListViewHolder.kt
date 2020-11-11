package com.example.desafio_android

import android.graphics.BitmapFactory
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL


class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: AppCompatTextView = itemView.findViewById(R.id.text_view_title)
    private val image: AppCompatImageView = itemView.findViewById(R.id.imageView)

    //fun bind(value: Int) {
        //title.text = "Number: $value"

    //}

    fun bind(link:String) {
        val link2 = "https://portalpopline.com.br/wp-content/uploads/2020/09/Britney-Spears-1.jpg"
        println("LINK "+link)
        println("LINK 2 "+link2)
        Picasso.get().load(link2).into(image)
        //title.text = "Number: $link2"
    }
}
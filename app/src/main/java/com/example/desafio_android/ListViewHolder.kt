package com.example.desafio_android

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: AppCompatTextView = itemView.findViewById(R.id.text_view_title)

    fun bind(value: Int) {
        title.text = "Number: $value"
    }
}
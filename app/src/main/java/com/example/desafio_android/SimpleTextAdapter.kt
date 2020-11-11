package com.example.desafio_android

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_text.view.*

class SimpleTextAdapter(
): RecyclerView.Adapter<SimpleTextAdapter.SimpleTextViewHolder>() {

    var list: ArrayList<String> = arrayListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    inner class SimpleTextViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(data: String) = with(itemView) {
            println("DATA BB "+data)
            //txt_simple_text.text = data

            //txt_simple_text.text = "power"

            //val image: AppCompatImageView = itemView.findViewById(R.id.imageView)
            val link2 = "https://portalpopline.com.br/wp-content/uploads/2020/09/Britney-Spears-1.jpg"
            Picasso.get().load(data).into(imageView3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SimpleTextViewHolder(parent)
    override fun getItemViewType(position: Int): Int = VIEW_ID
    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: SimpleTextViewHolder, position: Int) {
        holder.bind(list[position])
    }

    companion object {
        private const val VIEW_ID =
            R.layout.item_text
    }
}
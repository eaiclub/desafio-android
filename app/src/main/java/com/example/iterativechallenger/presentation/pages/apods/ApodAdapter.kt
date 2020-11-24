package com.example.iterativechallenger.presentation.pages.apods

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.iterativechallenger.R
import com.example.iterativechallenger.core.utils.Constantes
import com.example.iterativechallenger.databinding.ItemApodBinding
import com.example.iterativechallenger.domain.entities.Apod
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_apod.view.*
import java.io.Serializable

class ApodAdapter(
    private var listaApod : ArrayList<Apod>,
    private val onMoreClick :(apod : Apod) -> Unit) : RecyclerView.Adapter<ApodAdapter.ApodViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_apod, parent, false))
    }

    override fun getItemCount() = listaApod.size

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val binding = holder.binding
        val apod = listaApod[position]
        binding?.apod = apod
        binding?.executePendingBindings()

        if(apod.mediaType == Constantes.TIPO_IMAGE)
            Glide.with(holder.itemView.iv_picture.context).load(apod.url).centerCrop().into(holder.itemView.iv_picture)

        else if(apod.mediaType == Constantes.TIPO_VIDEO)
            Glide.with(holder.itemView.iv_picture.context).load(R.drawable.video_placeholder).centerCrop().into(holder.itemView.iv_picture)

        holder.itemView.iv_picture.setOnClickListener {
            onMoreClick(apod)
        }
    }
    fun setList(list:List<Apod>){
        this.listaApod.addAll(list)
        notifyDataSetChanged()
    }

//    @BindingAdapter("getImageFromUrl")
//    fun bindImage(imgView : ImageView, imageUrl : String){
//        Glide.with(imgView.context).load(imageUrl).into(imgView)
//
//    }


    inner class ApodViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding: ItemApodBinding? = DataBindingUtil.bind(view)
    }
}
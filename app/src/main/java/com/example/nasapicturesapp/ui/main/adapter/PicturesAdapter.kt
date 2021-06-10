package com.example.nasapicturesapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapicturesapp.databinding.ItemPictureBinding
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.util.loadUrl

class PicturesAdapter :
    PagingDataAdapter<PictureModel, PicturesAdapter.PictureViewHolder>(CharacterComparator) {

    class PictureViewHolder(private val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //TODO: make a beautiful layout
        fun bindPicture(item: PictureModel?) {
            binding.pictureImageView.loadUrl(item?.url.orEmpty())
            binding.apply {
                pictureTitle.text = item?.title ?: "UNKOWN"
            }
        }
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bindPicture(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(ItemPictureBinding.inflate(LayoutInflater.from(parent.context)))
    }

    object CharacterComparator : DiffUtil.ItemCallback<PictureModel>() {
        override fun areItemsTheSame(oldItem: PictureModel, newItem: PictureModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: PictureModel, newItem: PictureModel): Boolean {
            return oldItem == newItem
        }

    }
}
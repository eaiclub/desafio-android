package com.example.nasapicturesapp.ui.main.adapter

import android.animation.ValueAnimator
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapicturesapp.databinding.ItemPictureBinding
import com.example.nasapicturesapp.domain.model.PictureModel
import com.example.nasapicturesapp.util.loadUrl

class PicturesAdapter(private val onClick: (char: PictureModel) -> Unit) :
    PagingDataAdapter<PictureModel, PicturesAdapter.PictureViewHolder>(CharacterComparator) {

    override fun onViewAttachedToWindow(holder: PictureViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.animateCard()
    }

    class PictureViewHolder(private val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPicture(
            item: PictureModel,
            onClick: (char: PictureModel) -> Unit
        ) {
            binding.apply {
                pictureTitle.text = item.title
                root.setOnClickListener {
                    onClick(item)
                }
                binding.pictureImageView.loadUrl(item.url)
            }
        }

        fun animateCard() {
            val width = Resources.getSystem().displayMetrics.widthPixels
            val slideAnimator = ValueAnimator.ofInt(binding.pictureImageView.width, width)
                .setDuration(400)
            slideAnimator.addUpdateListener {
                binding.pictureImageView.layoutParams.width = it.animatedValue as Int
                binding.pictureImageView.requestLayout()
            }
            slideAnimator.start()
        }
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPicture(it, onClick) }
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
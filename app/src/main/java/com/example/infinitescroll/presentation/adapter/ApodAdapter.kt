package com.example.infinitescroll.presentation.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.infinitescroll.R
import com.example.infinitescroll.data.model.Apod
import com.example.infinitescroll.databinding.ItemApodBinding
import com.example.infinitescroll.presentation.ApodListFragmentDirections

class ApodAdapter : PagingDataAdapter<Apod, ApodAdapter.ApodViewHolder>(ApodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder(
                ItemApodBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class ApodViewHolder(
        private val binding: ItemApodBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Apod) {
            binding.apply {
                apod = item
                setClickListener {
                    val direction = ApodListFragmentDirections.actionApodListFragmentToApodDetailFragment(apod!!)
                    it.findNavController().navigate(direction)
                }
                executePendingBindings()
                progress.visibility = View.VISIBLE
            }
            if(item.hasImage())
                Glide.with(binding.image)
                    .load(item.url)
                    .override(100, 0)
                    .centerCrop()
                    .thumbnail(.05f)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                        ): Boolean {
                            binding.progress.visibility = View.GONE
                            return true
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                        ): Boolean {
                            binding.progress.visibility = View.GONE
                            return false
                        }
                    })
                    .into(binding.image)
            else {
                binding.progress.visibility = View.GONE
                binding.image.setImageResource(R.drawable.videoplaceholder)
            }
        }
    }
}

private class ApodDiffCallback : DiffUtil.ItemCallback<Apod>() {
    override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
        return oldItem.date.timeInMillis == newItem.date.timeInMillis
    }

    override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
        return oldItem == newItem
    }
}
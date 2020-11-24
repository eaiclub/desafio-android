package com.example.desafio_android.common.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.desafio_android.R
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.databinding.RowApodBinding
import com.example.desafio_android.util.IOnClickListener
import com.example.desafio_android.util.VIEW_ITEM
import com.example.desafio_android.util.VIEW_PROGRESS
import timber.log.Timber

class ApodListAdapter(apodsList: List<NasaApod>, private val clickListener: IOnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var loading = false

    private var mutableApodsList = mutableListOf<NasaApod>()

    init {
        mutableApodsList.addAll(apodsList)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mutableApodsList.size.minus(1) ) VIEW_PROGRESS else VIEW_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{

        return if (viewType == VIEW_ITEM) {
            val view: RowApodBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_apod, parent, false)
            ApodListViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mutableApodsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mutableApodsList[position]
        when(holder){
            is ApodListViewHolder -> {holder.bind(item); holder.rowLayout.setOnClickListener { clickListener.onClick(item) }}
            is ProgressViewHolder -> { holder.progressBar.isIndeterminate = true }
        }

        if (item.progress) {
            val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = true
        } else {
            val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = false
        }
    }

    inner class ApodListViewHolder internal constructor(private val binding: RowApodBinding): RecyclerView.ViewHolder(binding.root){
        val rowLayout = binding.rowLayout

        fun bind(item: NasaApod){
            binding.nasaApod = item
            binding.executePendingBindings()
        }
    }

    class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var progressBar: ProgressBar = v.findViewById<View>(R.id.progress_bar) as ProgressBar
    }

    fun setLoaded() {
        loading = false
        for (i in 0 until itemCount) {
            if (mutableApodsList[i].progress) {
                mutableApodsList.removeAt(i)
                notifyItemRemoved(i)
            }
        }
    }

    fun setLoading() {
        if (itemCount == 0 ) {
            val pokemon = mutableApodsList.first().copy().apply { this.progress = true }
            mutableApodsList.add(pokemon)
            notifyItemInserted(itemCount - 1)
            loading = true
        }
    }

    fun addMoreItems(newPokemonList: List<NasaApod>){
        mutableApodsList.addAll(newPokemonList)
        notifyDataSetChanged()
    }
}


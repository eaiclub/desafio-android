package com.example.desafio_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class NumberListAdapter(private val listenerBottom: () -> Unit) :
//    ListAdapter<Int, ListViewHolder>(ItemDiffCallback()) {
    ListAdapter<String, ListViewHolder>(ItemDiffCallback()) {

//    fun setList(list: List<Int>) {
//        this.submitList(ArrayList(list))
//    }
    fun setList(list: List<String>) {
        this.submitList(ArrayList(list))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        if (itemCount != 0) {
            if (position == (itemCount - 1)) {
                listenerBottom()
            }
        }
    }

}
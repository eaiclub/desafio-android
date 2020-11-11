package com.example.desafio_android

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.ItemCallback<String>() {
//class ItemDiffCallback : DiffUtil.ItemCallback<Int>() {
//    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
//        return oldItem == newItem
//    }
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        //return oldItem == newItem
        println("oldItem "+oldItem)
        println("newItem "+newItem)
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        //return oldItem == newItem
        println("oldItem "+oldItem)
        println("newItem "+newItem)
        return oldItem.equals(newItem)
    }
}
package com.lucasdonato.nasa.mechanism.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

abstract class BaseRecyclerAdapter<V, K : RecyclerView.ViewHolder> : RecyclerView.Adapter<K>() {

    protected val mData: MutableList<V> = ArrayList()

    var onItemClickListener: ((item: V) -> Unit)? = null

    var data: MutableList<V>
        get() = mData
        set(data) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }

    val isEmpty: Boolean
        get() = mData.isEmpty()

    abstract override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): K

    abstract override fun onBindViewHolder(k: K, position: Int)

    abstract fun validateDate(): Boolean

    override fun getItemCount(): Int {
        return mData.size
    }


    fun addEntity(entity: V) {
        mData.add(entity)
        notifyItemInserted(mData.size - 1)
    }

    fun deleteEntity(i: Int) {
        mData.removeAt(i)
        notifyItemRemoved(i)
        notifyItemRangeChanged(i, itemCount)
    }

    fun addAll(entities: List<V>) {
        mData.addAll(entities)
        notifyDataSetChanged()
    }

    private fun moveEntity(i: Int, loc: Int) {
        move(mData, i, loc)
        notifyItemMoved(i, loc)
    }

    private fun move(data: MutableList<V>, a: Int, b: Int) {
        val temp = data.removeAt(a)
        data.add(b, temp)
    }

    private fun getLocation(data: List<V>, entity: V): Int {
        for (j in data.indices) {
            val newEntity = data[j]
            if (entity == newEntity) {
                return j
            }
        }

        return -1
    }

}



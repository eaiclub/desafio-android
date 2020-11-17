package com.br.ibvn12.presentation.events.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.br.ibvn12.R
import com.br.ibvn12.data.remote.model.Events
import com.br.ibvn12.mechanism.base.BaseRecyclerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.view_card_events_list.view.*

class EventsRecyclerAdapter : BaseRecyclerAdapter<Events, EventsRecyclerAdapter.ViewHolder>() {

    private var eventsListener: EventsRecycleAdapterListener? = null

    fun setEventsRecyclerAdapterListener(listener: EventsRecycleAdapterListener) {
        eventsListener = listener
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mData[position], position)
    }

    fun setSelected(events: Events) {
        deselectedAllOptions()
        mData[mData.indexOf(events)].isSelected = true
        notifyDataSetChanged()
    }

    private fun deselectedAllOptions() {
        mData.forEach { option ->
            option.isSelected = false
        }
    }

    fun checkSelected() = mData.find { it.isSelected } != null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            (R.layout.view_card_events_list), viewGroup,
            false
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(events: Events, position: Int) {
            itemView.apply {

                name_event.text = events.name
                data_and_hour_event.text = events.data
                updateSelectableView(itemView, events)
                day_event.text = events.day


//                itemView.button_delete.setOnClickListener { petsListener?.onDeletePetListener(events) }
//                itemView.button_edit.setOnClickListener { petsListener?.onEditPetsListener(events) }

                container.setOnClickListener {
                    updateSelectableView(itemView, events)
                    setSelected(events)
                    onItemClickListener?.invoke(events)
                }
            }
        }

        private fun updateSelectableView(itemView: View, events: Events) {
            itemView.apply {
                if (events.isSelected) {
                    container.background =
                        ContextCompat.getDrawable(context, R.drawable.shape_events_card)
                } else {
                    container.background =
                        ContextCompat.getDrawable(context, R.drawable.shape_events_card)
                }
            }
        }
    }

    override fun validateDate() = false

    interface EventsRecycleAdapterListener {
        fun onDeleteEventsListener(events: Events)
        fun onEditEventsListener(events: Events)
    }

}
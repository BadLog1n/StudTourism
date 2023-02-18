package com.oneseed.studtourism.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.EventItemBinding
import com.oneseed.studtourism.ui.notifications.NotificationAdapter
import com.oneseed.studtourism.ui.notifications.NotificationData
import com.oneseed.studtourism.ui.search.TourismData

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    var eventsList = ArrayList<EventData>()

    class EventHolder (item: View) : RecyclerView.ViewHolder(item) {

        private val binding = EventItemBinding.bind(item)
        fun bind(eventItem: EventData) = with(binding) {

            //eventImage.drawable = eventItem.eventImage
            eventNameTv.text = eventItem.name
            eventCityTv.text = eventItem.city
            eventDateTv.text = eventItem.date

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)

        return EventsAdapter.EventHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addEvent(eventItem: EventData) {
        eventsList.add(eventItem)
        notifyDataSetChanged()

    }

    fun clearRecords() {
        eventsList.removeAll(eventsList.toSet())
        notifyItemRangeRemoved(0, eventsList.size)
        notifyItemRangeChanged(0, eventsList.size)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun removeObject(eventItem: EventData) {
        eventsList.remove(eventItem)
        notifyDataSetChanged()
    }

}
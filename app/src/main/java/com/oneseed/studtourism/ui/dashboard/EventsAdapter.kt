package com.oneseed.studtourism.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.EventItemBinding
import com.squareup.picasso.Picasso
import java.util.*

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventHolder>() {

    private var eventsList = ArrayList<EventData>()

    class EventHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = EventItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(eventItem: EventData) = with(binding) {
            try {
                Picasso.get().load(eventItem.eventImage).into(eventImage)
            } catch (e: Exception) {
                Picasso.get().load(R.drawable.ic__location_pin_).into(eventImage)
            }
            //eventImage.drawable = eventItem.eventImage
            eventNameTv.text = eventItem.name
            eventPriceTv.text = "Стоимость: ${eventItem.price} р."
            val dateFrom = getShortDate(eventItem.dateFrom.toLong())
            val dateTo = getShortDate(eventItem.dateTo.toLong())
            eventDateTv.text = "$dateFrom - $dateTo"


        }

        private fun getShortDate(ts: Long?): String {
            if (ts == null) return ""
            //Get instance of calendar
            val calendar = Calendar.getInstance(Locale.getDefault())
            //get current date from ts
            calendar.timeInMillis = ts
            //return formatted date
            return android.text.format.DateFormat.format("E, dd MMM yyyy", calendar).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        val imageHeart = view.findViewById<View>(R.id.eventHeartButton)
        val imageHeartFilled = view.findViewById<View>(R.id.heartFilledButton)

        imageHeart.setOnClickListener {
            imageHeartFilled.visibility = View.VISIBLE
            imageHeart.visibility = View.GONE
        }
        imageHeartFilled.setOnClickListener {
            imageHeartFilled.visibility = View.GONE
            imageHeart.visibility = View.VISIBLE
        }
        return EventHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    fun addEvent(eventItem: EventData) {
        eventsList.add(eventItem)
        notifyItemRangeChanged(0, eventsList.size)
    }

    fun clearRecords() {
        eventsList.removeAll(eventsList.toSet())
        notifyItemRangeRemoved(0, eventsList.size)
        notifyItemRangeChanged(0, eventsList.size)
    }


/*    @SuppressLint("NotifyDataSetChanged")
    fun removeObject(eventItem: EventData) {
        eventsList.remove(eventItem)
        notifyDataSetChanged()
    }*/

}
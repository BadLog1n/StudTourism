package com.oneseed.studtourism.ui.notifications

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.AccommodationItemBinding
import com.oneseed.studtourism.databinding.NotificationsItemBinding
import com.oneseed.studtourism.ui.search.AccommodationAdapter
import com.oneseed.studtourism.ui.search.TourismData

class NotificationAdapter: RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {

    var notificationList = ArrayList<NotificationData>()

    class NotificationHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = NotificationsItemBinding.bind(item)

        fun bind(notificationItem: NotificationData) = with(binding) {

            notificationName.text = notificationItem.name
            notifTextTv.text = notificationItem.text
            notifTime.text = notificationItem.time
            if (notificationItem.isWatched){
                notifBellImageview.setImageResource(R.drawable.ic_bell_green)
            }
            else {
                notifBellImageview.setImageResource(R.drawable.ic_bell)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notifications_item, parent, false)
        return NotificationAdapter.NotificationHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNotification(notificationItem: NotificationData) {
        notificationList.add(notificationItem)
        notifyDataSetChanged()
    }

    fun clearRecords() {
        notificationList.removeAll(notificationList.toSet())
    }


    @SuppressLint("NotifyDataSetChanged")
    fun removeObject(notificationItem: NotificationData) {
        notificationList.remove(notificationItem)
        notifyDataSetChanged()
    }
}
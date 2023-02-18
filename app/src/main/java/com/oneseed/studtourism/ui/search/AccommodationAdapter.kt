package com.oneseed.studtourism.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.AccommodationItemBinding
import com.squareup.picasso.Picasso

class AccommodationAdapter: RecyclerView.Adapter<AccommodationAdapter.AccomodationHolder>() {

    private var accommsList = ArrayList<TourismData>()

    class AccomodationHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = AccommodationItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(accommItem: TourismData) = with(binding) {
            try {
                Picasso.get().load(accommItem.photo).into(accommImage)
            } catch (e: Exception) {
                Picasso.get().load(R.drawable.ic__location_pin_).into(accommImage)
            }
            accommImage.drawable
            nameTv.text = accommItem.name
            cityTv.text = accommItem.city
            accommCostTv.text = "от ${accommItem.cost} руб/сутки"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccomodationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.accommodation_item, parent, false)



        return AccomodationHolder(view)
    }

    override fun getItemCount(): Int {
        return accommsList.size
    }

    override fun onBindViewHolder(holder: AccomodationHolder, position: Int) {
        holder.bind(accommsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAccomodation(accommItem: TourismData) {
        accommsList.add(accommItem)
        notifyDataSetChanged()

    }

    fun clearRecords() {
        accommsList.removeAll(accommsList.toSet())
        notifyItemRangeRemoved(0, accommsList.size)
        notifyItemRangeChanged(0, accommsList.size)
    }


    /*@SuppressLint("NotifyDataSetChanged")
    fun removeObject(accommItem: TourismData) {
        accommsList.remove(accommItem)
        notifyDataSetChanged()
    }*/
}
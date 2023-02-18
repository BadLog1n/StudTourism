package com.oneseed.studtourism.ui.search

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.AccommodationItemBinding
import com.squareup.picasso.Picasso

class AccommodationAdapter : RecyclerView.Adapter<AccommodationAdapter.AccomodationHolder>() {

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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.accommodation_item, parent, false)
        val layout = view.findViewById<View>(R.id.accommodationItemLayout)
        layout.setOnClickListener {
            val nameTv = view.findViewById<TextView>(R.id.name_tv).text.toString()
            val alert = AlertDialog.Builder(parent.context)
                .setTitle("Подать заявку")
                .setMessage("Подать заявку на проживание в $nameTv?")
                .setPositiveButton("Да") { _, _ ->
                }
                .setNegativeButton("Нет") { _, _ ->
                }
                .create()
            alert.show()
            //color of buttons black both

            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
            alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
        }
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
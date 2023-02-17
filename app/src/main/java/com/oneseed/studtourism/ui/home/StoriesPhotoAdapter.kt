package com.oneseed.studtourism.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oneseed.studtourism.R
import com.oneseed.studtourism.databinding.StoriesItemBinding

class StoriesPhotoAdapter : RecyclerView.Adapter<StoriesPhotoAdapter.StoriesHolder>() {

    var recordsList = ArrayList<StoriesRecord>()
    class StoriesHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = StoriesItemBinding.bind(item)

        fun bind(photoRecord: StoriesRecord) {
           binding.image.setImageResource(photoRecord.photo)
        }

    }

    fun addPhotoRecord(feedRecord: StoriesRecord) {
        recordsList.add(feedRecord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stories_item, parent, false)

        return StoriesHolder(view)
    }

    override fun onBindViewHolder(holder: StoriesHolder, position: Int) {
        holder.bind(recordsList[position])
    }

    override fun getItemCount(): Int {
        return recordsList.size
    }
}

data class StoriesRecord(
    val photo: Int
)

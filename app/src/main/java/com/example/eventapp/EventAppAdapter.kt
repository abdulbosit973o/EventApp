package com.example.eventapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eventapp.databinding.ItemMusicBinding

class EventAppAdapter :
    ListAdapter<EventData, EventAppAdapter.AllBookViewHolder>(
        AllBookDiffUtil
    ) {


    private lateinit var clickItem: (EventData) -> Unit

    object AllBookDiffUtil : DiffUtil.ItemCallback<EventData>() {
        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean =
            oldItem.id   == newItem.id  

        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean =
            oldItem == newItem

    }

    inner class AllBookViewHolder(val binding: ItemMusicBinding) :
        ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickItem.invoke(getItem(adapterPosition))
            }
        }

        fun bind(data: EventData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllBookViewHolder =
        AllBookViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllBookViewHolder, position: Int) =
        holder.bind(getItem(position))


    fun setOnClickItem(block: (EventData) -> Unit) {
        clickItem = block
    }

}
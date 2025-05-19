package com.example.moodtracker.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtracker.R
import com.example.moodtracker.data.MoodEntry
import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder

class MoodAdapter (
    val itemList: MutableList<MoodEntry>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<MoodAdapter.ItemViewHolder>(){
    interface OnItemClickListener {
        fun onItemClick(moodEntry: MoodEntry)
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemFeel: TextView = itemView.findViewById(R.id.mood_item_feel)
        val itemType: TextView = itemView.findViewById(R.id.mood_item_type)
        val itemRating: RatingBar = itemView.findViewById(R.id.mood_item_rating)
        val itemEmoji: TextView = itemView.findViewById(R.id.mood_item_emoji)
        val itemScheme: LinearLayout = itemView.findViewById(R.id.mood_schemes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemFeel.text = currentItem.feeling
        holder.itemType.text = currentItem.type
        holder.itemEmoji.text = when (currentItem.feeling){
            "Zadowolony" -> ":-D"
            "Moze byc" -> ":/"
            "Smutny" -> ":("
            else -> ":0"
        }
        holder.itemRating.rating = currentItem.rating

        holder.itemScheme.setBackgroundResource(
            when(currentItem.feeling){
                "Zadowolony" -> R.color.white
                "Moze byc" -> R.color.black
                "Smutny" -> R.color.black
                else -> androidx.browser.R.color.browser_actions_divider_color
            }
        )

        holder.itemView.setOnClickListener{
            listener.onItemClick(currentItem)
        }
    }
    override fun getItemCount(): Int = itemList.size
}
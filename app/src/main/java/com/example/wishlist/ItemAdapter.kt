package com.example.wishlist

import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items:MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView

        init {
            nameTextView = itemView.findViewById<TextView>(R.id.itemName)
            priceTextView = itemView.findViewById<TextView>(R.id.price)
            urlTextView = itemView.findViewById<TextView>(R.id.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val email = items.get(position)
        // Set item views based on views and data model
        holder.nameTextView.text = email.name
        holder.priceTextView.text = email.price.toString()
        holder.urlTextView.text = email.url
    }

    fun addItem(newItem: Item) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

}
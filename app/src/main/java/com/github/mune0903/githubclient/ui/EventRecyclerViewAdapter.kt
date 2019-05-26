package com.github.mune0903.githubclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.databinding.ItemEventBinding

class EventRecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder>() {

    val userActions = ArrayList<Event>()

    override fun getItemCount(): Int {
        return userActions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        val binding = ItemEventBinding.bind(v)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = userActions[position]
        holder.binding.event = event
    }

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }

    class ViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)
}
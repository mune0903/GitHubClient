package com.github.mune0903.githubclient.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.databinding.ItemNewsBinding

class NewsRecyclerAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    val newsList = ArrayList<News>()

    override fun getItemCount(): Int = newsList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = newsList[position]
        holder.binding.news = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        val binding = ItemNewsBinding.bind(v)
        return ViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick()
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}
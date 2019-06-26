package com.github.mune0903.githubclient.ui.news.item

import com.github.mune0903.data.model.News
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.databinding.ItemNewsBinding
import com.xwray.groupie.databinding.BindableItem

data class NewsItem(
    private val newsList: News
) : BindableItem<ItemNewsBinding>() {

    override fun getLayout(): Int = R.layout.item_news

    override fun bind(viewBinding: ItemNewsBinding, position: Int) {
        viewBinding.news = newsList
    }

    override fun getId(): Long = newsList.id.toLong()
}
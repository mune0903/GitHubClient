package com.github.mune0903.githubclient.ui

import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.databinding.ItemNewsBinding
import com.xwray.groupie.databinding.BindableItem

data class NewsItem(
    val news: News
) : BindableItem<ItemNewsBinding>() {

    override fun getLayout() = R.layout.item_news

    override fun bind(viewBinding: ItemNewsBinding, position: Int) {
        viewBinding.news = news
    }
}
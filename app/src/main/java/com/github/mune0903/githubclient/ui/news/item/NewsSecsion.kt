package com.github.mune0903.githubclient.ui.news.item

import com.github.mune0903.data.model.News
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class NewsSecsion : Section() {
    fun updateNews(newsList: List<News>) {
        val list = mutableListOf<Item<*>>()
        newsList.mapTo(list) {
                NewsItem(it)
            }
        update(list)
    }
}
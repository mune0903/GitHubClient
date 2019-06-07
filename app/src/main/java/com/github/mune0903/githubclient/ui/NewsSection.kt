package com.github.mune0903.githubclient.ui

import com.github.mune0903.githubclient.data.remote.model.News
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class NewsSection : Section() {
    fun updateNews(newsList: List<News>) {
        val list = mutableListOf<Item<*>>()
        newsList.mapTo(list) {
            NewsItem(it)
        }
        update(list)
    }
}
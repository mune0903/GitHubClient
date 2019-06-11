package com.github.mune0903.githubclient.ui.news.item

import com.github.mune0903.githubclient.data.remote.model.News
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class NewsSection : Section() {
    fun updateNews(staffList: List<News>) {
        val list = mutableListOf<Item<*>>()
        staffList.mapTo(list) {
            NewsItem(it)
        }
        update(list)
    }
}
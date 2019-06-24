package com.github.mune0903.githubclient.ui.news

import com.airbnb.epoxy.TypedEpoxyController
import com.github.mune0903.data.model.News
import com.github.mune0903.githubclient.ItemNewsBindingModel_

data class NewsItemController(
    val newsList: List<News> = emptyList()
) : TypedEpoxyController<NewsItemController>() {

    override fun buildModels(data: NewsItemController?) {
        data ?: return

        data.newsList.forEach {
            ItemNewsBindingModel_()
                .news(it)
                .id(it.id)
                .addTo(this)
        }
    }
}
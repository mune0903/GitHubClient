package com.github.mune0903.data.remote.github

import com.github.mune0903.data.model.News
import com.github.mune0903.data.model.User
import io.reactivex.Observable

interface GitHubRepository {
    fun getNews(userName: String): Observable<List<News>>

    fun getUser(): Observable<User>
}
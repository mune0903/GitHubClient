package com.github.mune0903.githubclient.data.remote.github

import com.github.mune0903.githubclient.data.model.News
import com.github.mune0903.githubclient.data.model.User
import io.reactivex.Observable

interface GitHubRepository {
    fun getNews(userName: String): Observable<List<News>>

    fun getUser(): Observable<User>
}
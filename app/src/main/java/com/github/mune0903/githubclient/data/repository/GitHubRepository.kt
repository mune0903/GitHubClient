package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.data.remote.model.User
import io.reactivex.Observable

interface GitHubRepository {
    fun isLoggedIn(): Boolean

    fun getToken(clientId: String, clientSecret: String, code: String): Observable<Token>

    fun saveToken(token: String)

    fun getNews(userName: String): Observable<List<News>>

    fun getUser(): Observable<User>
}
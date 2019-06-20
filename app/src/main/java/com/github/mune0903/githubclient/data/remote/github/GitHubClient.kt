package com.github.mune0903.githubclient.data.remote.github

import com.github.mune0903.githubclient.data.model.News
import com.github.mune0903.githubclient.data.model.Token
import com.github.mune0903.githubclient.data.model.User
import com.github.mune0903.githubclient.data.remote.*
import io.reactivex.Observable
import retrofit2.http.*

interface GitHubClient {
    @GET(URL_GET_NEWS)
    fun getNews(
        @Header(TOKEN_HEADER) header: String,
        @Path("user_name") userName: String
    ): Observable<List<News>>

    @GET(URL_GET_USER)
    fun getUser(
        @Header(TOKEN_HEADER) header: String
    ): Observable<User>
}
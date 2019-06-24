package com.github.mune0903.data.remote.github

import com.github.mune0903.data.model.News
import com.github.mune0903.data.model.User
import com.github.mune0903.data.remote.TOKEN_HEADER
import com.github.mune0903.data.remote.URL_GET_NEWS
import com.github.mune0903.data.remote.URL_GET_USER
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

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
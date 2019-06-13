package com.github.mune0903.githubclient.data.remote.client

import com.github.mune0903.githubclient.data.remote.*
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.data.remote.model.User
import io.reactivex.Observable
import retrofit2.http.*

interface GitHubClient {
    @POST(URL_GET_TOKEN)
    @FormUrlEncoded
    fun getToken(
        @Header(ACCEPT_HEADER) header: String,
        @FieldMap body: Map<String, String>
    ): Observable<Token>

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
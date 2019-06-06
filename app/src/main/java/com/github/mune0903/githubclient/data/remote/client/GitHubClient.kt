package com.github.mune0903.githubclient.data.remote.client

import com.github.mune0903.githubclient.data.remote.ACCEPT_HEADER
import com.github.mune0903.githubclient.data.remote.NEWS
import com.github.mune0903.githubclient.data.remote.TOKEN_HEADER
import com.github.mune0903.githubclient.data.remote.URL_GET_TOKEN
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.Token
import io.reactivex.Observable
import retrofit2.http.*

interface GitHubClient {
    @POST(URL_GET_TOKEN)
    @FormUrlEncoded
    fun getToken(
        @Header(ACCEPT_HEADER) header: String,
        @FieldMap body: Map<String, String>
    ): Observable<Token>

    @GET(NEWS)
    fun getNews(
        @Header(TOKEN_HEADER) header: String,
        @Path("user_name") userName: String
    ): Observable<List<News>>
}
package com.github.mune0903.githubclient.data.remote.oauth

import com.github.mune0903.githubclient.data.model.Token
import com.github.mune0903.githubclient.data.remote.ACCEPT_HEADER
import com.github.mune0903.githubclient.data.remote.URL_GET_TOKEN
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface OAuthClient {
    @POST(URL_GET_TOKEN)
    @FormUrlEncoded
    fun getToken(
        @Header(ACCEPT_HEADER) header: String,
        @FieldMap body: Map<String, String>
    ): Observable<Token>
}
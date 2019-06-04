package com.github.mune0903.githubclient.data.remote.client

import com.github.mune0903.githubclient.data.remote.ACCEPT_HEADER
import com.github.mune0903.githubclient.data.remote.EVENT
import com.github.mune0903.githubclient.data.remote.URL_GET_TOKEN
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.remote.model.Token
import io.reactivex.Observable
import retrofit2.http.*

interface GitHubClient {
    @POST(URL_GET_TOKEN)
    @FormUrlEncoded
    fun getToken(@Header(ACCEPT_HEADER) header: String, @FieldMap body: Map<String, String>): Observable<Token>
    @GET(EVENT)
    fun get(): Observable<List<Event>>
}
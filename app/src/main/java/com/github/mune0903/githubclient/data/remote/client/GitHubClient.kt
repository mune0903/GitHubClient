package com.github.mune0903.githubclient.data.remote.client

import com.github.mune0903.githubclient.data.remote.EVENT
import com.github.mune0903.githubclient.data.remote.model.Event
import retrofit2.http.GET
import io.reactivex.Observable

interface GitHubClient {

    @GET(EVENT)
    fun get(): Observable<List<Event>>
}
package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.client.GitHubClient
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.Observable
import retrofit2.Retrofit

class GitHubRepositoryImpl(
    private val retrofit: Retrofit
) : GitHubRepository {

    private val client by lazy { retrofit.create(GitHubClient::class.java) }

    override fun getEvent(): Observable<List<Event>> {
        return client.get()
            .observeOnMainThread()
    }
}
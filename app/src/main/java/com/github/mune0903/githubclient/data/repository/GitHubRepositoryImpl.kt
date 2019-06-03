package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.client.GitHubClient
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.Observable
import retrofit2.Retrofit

class GitHubRepositoryImpl(
    private val retrofit: Retrofit
) : GitHubRepository {

    private val client by lazy { retrofit.create(GitHubClient::class.java) }

    override fun getToken(clientId: String, clientSecret: String, code: String): Observable<Token> {
        val request = mapOf(
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "code" to code
        )
        return client.getToken("application/json", request)
            .observeOnMainThread()
    }

    override fun getEventList(): Observable<List<Event>> {
        return client.get()
            .observeOnMainThread()
    }
}
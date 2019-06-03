package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.remote.model.Token
import io.reactivex.Observable

interface GitHubRepository {
    fun getToken(clientId: String, clientSecret: String, code: String):  Observable<Token>

    fun getEventList(): Observable<List<Event>>
}
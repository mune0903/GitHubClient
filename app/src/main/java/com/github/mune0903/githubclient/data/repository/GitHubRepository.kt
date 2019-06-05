package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.remote.model.Token
import io.reactivex.Completable
import io.reactivex.Observable

interface GitHubRepository {
    fun isLoggedIn(): Boolean

    fun getToken(clientId: String, clientSecret: String, code: String):  Observable<Token>

    fun saveToken(token: String)

    fun getEventList(): Observable<List<Event>>
}
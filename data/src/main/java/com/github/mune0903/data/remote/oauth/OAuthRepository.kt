package com.github.mune0903.data.remote.oauth

import com.github.mune0903.data.model.Token
import io.reactivex.Observable

interface OAuthRepository {
    fun isLoggedIn(): Boolean

    fun getToken(clientId: String, clientSecret: String, code: String): Observable<Token>

    fun saveToken(token: String)
}
package com.github.mune0903.data.remote.oauth

import android.content.Context
import androidx.core.content.edit
import com.github.mune0903.common.observeOnMainThread
import com.github.mune0903.data.model.Token
import io.reactivex.Observable
import retrofit2.Retrofit

class OAuthRepositoryImpl(
    private val context: Context,
    private val retrofit: Retrofit
) : OAuthRepository {

    private val client by lazy { retrofit.create(OAuthClient::class.java) }

    private val tokenKey = "token"

    private val sharedPreferences = context.getSharedPreferences(tokenKey, Context.MODE_PRIVATE)

    override fun isLoggedIn(): Boolean {
        sharedPreferences.getString(tokenKey, "").let {
            return !it.isNullOrEmpty()
        }
    }

    override fun getToken(clientId: String, clientSecret: String, code: String): Observable<Token> {
        val request = mapOf(
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "code" to code
        )
        return client.getToken("application/json", request)
            .observeOnMainThread()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(tokenKey, token)
        }
    }
}
package com.github.mune0903.githubclient.data.repository

import android.content.Context
import androidx.core.content.edit
import com.github.mune0903.githubclient.data.remote.client.GitHubClient
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.Retrofit

class GitHubRepositoryImpl(
    private val retrofit: Retrofit,
    private val context: Context
) : GitHubRepository {

    private val preferenceKey = "githubclient"

    private val tokenKey = "token"

    private val client by lazy { retrofit.create(GitHubClient::class.java) }

    private val sharedPreferences = context.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

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

    override fun saveToken(token: String): Completable {
        return Completable.create { emitter ->
            sharedPreferences.edit {
                putString(tokenKey, token)
            }
            emitter.onComplete()
        }
    }

    override fun getEventList(): Observable<List<Event>> {
        return client.get()
            .observeOnMainThread()
    }
}
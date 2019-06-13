package com.github.mune0903.githubclient.data.repository

import android.content.Context
import androidx.core.content.edit
import com.github.mune0903.githubclient.data.remote.client.GitHubClient
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.data.remote.model.User
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.Observable
import retrofit2.Retrofit

class GitHubRepositoryImpl(
    private val retrofitOAuth: Retrofit,
    private val retrofitAPI: Retrofit,
    private val context: Context
) : GitHubRepository {

    private val preferenceKey = "githubclient"

    private val tokenKey = "token"

    private val clientOAuth by lazy { retrofitOAuth.create(GitHubClient::class.java) }

    private val clientAPI by lazy { retrofitAPI.create(GitHubClient::class.java) }

    private val sharedPreferences = context.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

    private val token = sharedPreferences.getString(tokenKey, "")

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
        return clientOAuth.getToken("application/json", request)
            .observeOnMainThread()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(tokenKey, token)
        }
    }

    override fun getNews(userName: String): Observable<List<News>> {
        return clientAPI.getNews("token $token", userName)
            .observeOnMainThread()
    }

    override fun getUser(): Observable<User> {
        return clientAPI.getUser("token $token")
            .observeOnMainThread()
    }
}
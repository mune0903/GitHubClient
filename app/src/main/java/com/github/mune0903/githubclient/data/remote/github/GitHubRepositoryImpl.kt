package com.github.mune0903.githubclient.data.remote.github

import android.content.Context
import com.github.mune0903.githubclient.data.model.News
import com.github.mune0903.githubclient.data.model.User
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.Observable
import retrofit2.Retrofit

class GitHubRepositoryImpl(
    private val context: Context,
    private val retrofit: Retrofit
) : GitHubRepository {

    private val preferenceKey = "githubclient"

    private val tokenKey = "token"

    private val client by lazy { retrofit.create(GitHubClient::class.java) }

    private val sharedPreferences = context.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

    private val token = sharedPreferences.getString(tokenKey, "")

    override fun getNews(userName: String): Observable<List<News>> {
        return client.getNews("token $token", userName)
            .observeOnMainThread()
    }

    override fun getUser(): Observable<User> {
        return client.getUser("token $token")
            .observeOnMainThread()
    }
}
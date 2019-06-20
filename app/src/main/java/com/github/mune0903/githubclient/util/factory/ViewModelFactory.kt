package com.github.mune0903.githubclient.util.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.mune0903.githubclient.data.remote.BASE_API_URL
import com.github.mune0903.githubclient.data.remote.BASE_OAUTH_URL
import com.github.mune0903.githubclient.data.remote.github.GitHubRepository
import com.github.mune0903.githubclient.data.remote.github.GitHubRepositoryImpl
import com.github.mune0903.githubclient.data.remote.oauth.OAuthRepository
import com.github.mune0903.githubclient.data.remote.oauth.OAuthRepositoryImpl
import com.github.mune0903.githubclient.ui.MainViewModel
import com.github.mune0903.githubclient.ui.news.NewsViewModel
import com.github.mune0903.githubclient.ui.oauth.OAuthViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private fun createRetrofitOAuth(): Retrofit {
        val okHttp = OkHttpClient()
            .newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val moshi = Moshi.Builder().build()

        return Retrofit.Builder()
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_OAUTH_URL)
            .build()
    }

    private fun createRetrofitAPI(): Retrofit {
        val okHttp = OkHttpClient()
            .newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val moshi = Moshi.Builder().build()

        return Retrofit.Builder()
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_API_URL)
            .build()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val retrofitOAuth = createRetrofitOAuth()
        val retrofitAPI = createRetrofitAPI()

        val oauthRepository: OAuthRepository = OAuthRepositoryImpl(retrofitOAuth, context)
        val githubRepository: GitHubRepository = GitHubRepositoryImpl(retrofitAPI, context)

        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(githubRepository) as T
        } else if (modelClass == OAuthViewModel::class.java) {
            return OAuthViewModel(oauthRepository) as  T
        } else if (modelClass == NewsViewModel::class.java) {
            return NewsViewModel(githubRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
    }
}
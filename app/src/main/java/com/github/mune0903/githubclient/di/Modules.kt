package com.github.mune0903.githubclient.di

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
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Modules {
    val apiModule = module {
        single {
            OkHttpClient()
                .newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        }

        single {
            Moshi.Builder().build()
        }

        factory(named("oauth")) {
            Retrofit.Builder()
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_OAUTH_URL)
                .build()
        }

        factory(named("api")) {
            Retrofit.Builder()
                .client(get())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_API_URL)
                .build()
        }
    }

    val repositoryModule = module {
        single<OAuthRepository> { OAuthRepositoryImpl(get(), get(named("oauth"))) }
        single<GitHubRepository> { GitHubRepositoryImpl(get(), get(named("api"))) }
    }

    val viewModelModule = module {
        factory { MainViewModel(get()) }
        factory { OAuthViewModel(get()) }
        factory { NewsViewModel(get()) }
    }
}
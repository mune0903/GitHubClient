package com.github.mune0903.githubclient.di

import com.github.mune0903.githubclient.data.remote.github.GitHubRepository
import com.github.mune0903.githubclient.data.remote.github.GitHubRepositoryImpl
import com.github.mune0903.githubclient.data.remote.oauth.OAuthRepository
import com.github.mune0903.githubclient.data.remote.oauth.OAuthRepositoryImpl
import com.github.mune0903.githubclient.ui.MainViewModel
import com.github.mune0903.githubclient.ui.news.NewsViewModel
import com.github.mune0903.githubclient.ui.oauth.OAuthViewModel
import org.koin.dsl.module

val appModule = module {

    single<OAuthRepository> { OAuthRepositoryImpl(get(), get()) }
    single<GitHubRepository> { GitHubRepositoryImpl(get(), get()) }

    factory { MainViewModel(get()) }
    factory { OAuthViewModel(get()) }
    factory { NewsViewModel(get()) }
}
package com.github.mune0903.githubclient.di

import com.github.mune0903.githubclient.data.repository.GitHubRepository
import com.github.mune0903.githubclient.data.repository.GitHubRepositoryImpl
import com.github.mune0903.githubclient.ui.MainViewModel
import com.github.mune0903.githubclient.ui.news.NewsViewModel
import com.github.mune0903.githubclient.ui.oauth.OAuthViewModel
import org.koin.dsl.module

val appModule = module {

    single<GitHubRepository> { GitHubRepositoryImpl(get(), get(), get()) }

    factory { MainViewModel(get()) }
    factory { OAuthViewModel(get()) }
    factory { NewsViewModel(get()) }
}
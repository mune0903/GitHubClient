package com.github.mune0903.githubclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.remote.client.GitHubClient
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class MainViewModel(
    private val gitHubClient: GitHubClient
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    fun getNewsList(token: String, userName: String) {
        gitHubClient.getNews(token, userName)
            .observeOnMainThread()
            .subscribe({
                _news.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

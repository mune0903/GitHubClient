package com.github.mune0903.githubclient.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.model.News
import com.github.mune0903.githubclient.data.remote.github.GitHubRepository
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class NewsViewModel(
    private val githubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    fun getNewsList(userName: String) {
        githubRepository.getNews(userName)
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
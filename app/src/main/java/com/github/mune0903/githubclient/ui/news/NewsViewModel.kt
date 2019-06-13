package com.github.mune0903.githubclient.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.User
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class NewsViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun getNewsList(userName: String) {
        gitHubRepository.getNews(userName)
            .observeOnMainThread()
            .subscribe({
                _news.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    fun getUser() {
        gitHubRepository.getUser()
            .observeOnMainThread()
            .subscribe({
                _user.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
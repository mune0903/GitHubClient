package com.github.mune0903.githubclient.ui

import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

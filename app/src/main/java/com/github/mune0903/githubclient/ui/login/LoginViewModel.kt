package com.github.mune0903.githubclient.ui.login

import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
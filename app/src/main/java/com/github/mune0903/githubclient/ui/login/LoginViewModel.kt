package com.github.mune0903.githubclient.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _oauthUrl = MutableLiveData<String>()
    val oauthUrl: LiveData<String> = _oauthUrl

    fun oauth() {
        _oauthUrl.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
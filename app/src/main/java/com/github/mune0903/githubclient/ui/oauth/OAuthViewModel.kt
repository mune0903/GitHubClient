package com.github.mune0903.githubclient.ui.oauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.CLIENT_SECRET
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class OAuthViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token> = _token

    fun isLoggedIn(): Boolean {
        return gitHubRepository.isLoggedIn()
    }

    fun getToken(code: String) {
        gitHubRepository.getToken(CLIENT_ID, CLIENT_SECRET, code)
            .observeOnMainThread()
            .subscribe({
                _token.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    fun saveToken(token: String) {
        gitHubRepository.saveToken(token)
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
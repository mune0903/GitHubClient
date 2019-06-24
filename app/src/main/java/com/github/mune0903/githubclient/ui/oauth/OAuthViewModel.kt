package com.github.mune0903.githubclient.ui.oauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.common.extension.observeOnMainThread
import com.github.mune0903.data.model.Token
import com.github.mune0903.data.remote.oauth.OAuthRepository
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.CLIENT_SECRET
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class OAuthViewModel(
    private val oauthRepository: OAuthRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token> = _token

    fun isLoggedIn(): Boolean {
        return oauthRepository.isLoggedIn()
    }

    fun getToken(code: String) {
        oauthRepository.getToken(CLIENT_ID, CLIENT_SECRET, code)
            .observeOnMainThread()
            .subscribe({
                _token.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    fun saveToken(token: String) {
        oauthRepository.saveToken(token)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
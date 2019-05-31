package com.github.mune0903.githubclient.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

package com.github.mune0903.githubclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.data.repository.GitHubRepository
import com.github.mune0903.githubclient.util.extension.observeOnMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class MainViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> = _event

    fun getEvent() {
        gitHubRepository.getEventList()
            .observeOnMainThread()
            .subscribe({
                _event.value = it
            }, {
                Timber.e(it)
            }).addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

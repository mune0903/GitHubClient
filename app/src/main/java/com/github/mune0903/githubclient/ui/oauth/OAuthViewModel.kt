package com.github.mune0903.githubclient.ui.oauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OAuthViewModel : ViewModel() {

    private val _onButtonClick = MutableLiveData<Unit>()
    val onButtonClick: LiveData<Unit> = _onButtonClick

    fun onButtonClick() {
        _onButtonClick.value = Unit
    }
}
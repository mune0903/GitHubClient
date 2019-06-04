package com.github.mune0903.githubclient.ui.oauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.CLIENT_SECRET
import com.github.mune0903.githubclient.data.repository.GitHubRepository

class OAuthViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    fun getToken(code: String) {
        gitHubRepository.getToken(CLIENT_ID, CLIENT_SECRET, code)
    }

}
package com.github.mune0903.githubclient.ui.oauth

import androidx.lifecycle.ViewModel
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.CLIENT_SECRET
import com.github.mune0903.githubclient.data.repository.GitHubRepository

class OAuthViewModel(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return gitHubRepository.isLoggedIn()
    }

    fun getToken(code: String) {
        gitHubRepository.getToken(CLIENT_ID, CLIENT_SECRET, code)
    }
}
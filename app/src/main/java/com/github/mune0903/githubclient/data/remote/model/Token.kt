package com.github.mune0903.githubclient.data.remote.model

data class Token(
    val access_token: String,
    val scope: String,
    val token_type: String
)
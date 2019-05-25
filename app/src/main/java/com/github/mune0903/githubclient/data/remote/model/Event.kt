package com.github.mune0903.githubclient.data.remote.model

data class Event(
    val type: String,
    val actor: Actor,
    val repo: Repo
)
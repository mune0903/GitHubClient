package com.github.mune0903.githubclient.data.repository

import com.github.mune0903.githubclient.data.remote.model.Event
import io.reactivex.Observable

interface GitHubRepository {
    fun getEvent(): Observable<List<Event>>
}
package com.github.mune0903.githubclient

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.mune0903.githubclient.di.Modules.apiModule
import com.github.mune0903.githubclient.di.Modules.repositoryModule
import com.github.mune0903.githubclient.di.Modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(
                apiModule, repositoryModule, viewModelModule
            ))
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}
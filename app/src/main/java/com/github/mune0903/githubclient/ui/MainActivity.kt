package com.github.mune0903.githubclient.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.ui.news.NewsFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel

        // Fragmentをセット
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.newInstance(), NewsFragment.TAG)
            .commit()
    }

    companion object {
        fun createIntent(activity: Activity) = Intent(activity, MainActivity::class.java)
    }
}
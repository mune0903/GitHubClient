package com.github.mune0903.githubclient.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.databinding.ActivityMainBinding
import com.github.mune0903.githubclient.ui.news.NewsFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.newInstance(), NewsFragment.TAG)
            .commit()
    }

    companion object {
        fun createIntent(activity: Activity) = Intent(activity, MainActivity::class.java)
    }
}
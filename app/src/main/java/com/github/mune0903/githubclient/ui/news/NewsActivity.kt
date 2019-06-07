package com.github.mune0903.githubclient.ui.news

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.ui.MainActivity

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Fragmentをセット
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.newInstance(), NewsFragment.TAG)
            .commit()
    }

    companion object {
        fun createIntent(activity: Activity) = Intent(activity, NewsActivity::class.java)
    }
}
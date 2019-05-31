package com.github.mune0903.githubclient.ui.oauth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mune0903.githubclient.R

class OAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, OAuthFragment.newInstance(), OAuthFragment.TAG)
            .commit()
    }
}
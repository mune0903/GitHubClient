package com.github.mune0903.githubclient.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mune0903.githubclient.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG)
            .commit()
    }
}
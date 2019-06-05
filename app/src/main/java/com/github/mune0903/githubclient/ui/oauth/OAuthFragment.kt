package com.github.mune0903.githubclient.ui.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.github.mune0903.githubclient.OAUTH_URL
import com.github.mune0903.githubclient.ui.MainActivity
import com.github.mune0903.githubclient.util.factory.ViewModelFactory

class OAuthFragment : Fragment() {

    private val viewModelFactory = ViewModelFactory()

    private val viewModel: OAuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OAuthViewModel::class.java)
    }

    private val args: OAuthFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.isLoggedIn()) {
            transitToMain()
            return
        }

        args.code?.let { code ->
            viewModel.getToken(code)
        } ?: run {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_URL))
            startActivity(intent)
        }
    }

    private fun transitToMain() {
        val intent = MainActivity.createIntent(requireActivity())
        startActivity(intent)
        requireActivity().finish()
    }

}
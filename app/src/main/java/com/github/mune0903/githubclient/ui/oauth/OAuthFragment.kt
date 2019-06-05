package com.github.mune0903.githubclient.ui.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.github.mune0903.githubclient.OAUTH_URL
import com.github.mune0903.githubclient.data.remote.model.Token
import com.github.mune0903.githubclient.ui.MainActivity
import com.github.mune0903.githubclient.util.factory.ViewModelFactory

class OAuthFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(requireContext())
    }

    private val viewModel: OAuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OAuthViewModel::class.java)
    }

    private val args: OAuthFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            token.observe(this@OAuthFragment, Observer { accessToken ->
                accessToken?.let {
                    saveToken(it.access_token)
                }
            })
        }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun transitToMain() {
        val intent = MainActivity.createIntent(requireActivity())
        startActivity(intent)
        requireActivity().finish()
    }
}
package com.github.mune0903.githubclient.ui.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.OAUTH_URL
import com.github.mune0903.githubclient.REDIRECT_URI
import com.github.mune0903.githubclient.util.factory.ViewModelFactory


class OAuthFragment : Fragment() {

    private lateinit var viewModel: OAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OAuthViewModel::class.java)

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(OAUTH_URL + "?client_id=" + CLIENT_ID + "&scope=repo&redirect_uri=" + REDIRECT_URI)
        )
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {

        val TAG: String = OAuthFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = OAuthFragment()
    }

}
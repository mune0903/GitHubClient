package com.github.mune0903.githubclient.ui.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.github.mune0903.githubclient.OAUTH_URL
import com.github.mune0903.githubclient.util.factory.ViewModelFactory
import timber.log.Timber


class OAuthFragment : Fragment() {

    private lateinit var viewModel: OAuthViewModel

    private val args: OAuthFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OAuthViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.code?.let {
            Timber.d("here")
        } ?: run {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_URL))
            startActivity(intent)
        }
    }

    companion object {

        val TAG: String = OAuthFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = OAuthFragment()
    }

}
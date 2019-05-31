package com.github.mune0903.githubclient.ui.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mune0903.githubclient.CLIENT_ID
import com.github.mune0903.githubclient.OAUTH_URL
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.REDIRECT_URI
import com.github.mune0903.githubclient.databinding.FragmentOauthBinding
import com.github.mune0903.githubclient.util.factory.ViewModelFactory

class OAuthFragment : Fragment() {

    private lateinit var binding: FragmentOauthBinding

    private lateinit var viewModel: OAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OAuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_oauth, container, false)
        binding = FragmentOauthBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setupWebView()
        binding.oauthButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(OAUTH_URL + "?client_id=" + CLIENT_ID + "&scope=repo&redirect_uri=" + REDIRECT_URI)
            )
            startActivity(intent)
        }
    }

    companion object {

        val TAG: String = OAuthFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = OAuthFragment()
    }

}
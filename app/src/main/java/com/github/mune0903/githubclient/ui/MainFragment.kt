package com.github.mune0903.githubclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.databinding.FragmentMainBinding
import com.github.mune0903.githubclient.databinding.FragmentNewsBinding
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.user.observe(this, Observer {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drawer_header, container, false)
        binding = FragmentMainBinding.bind(view)
        return binding.root
    }
}
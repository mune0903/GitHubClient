package com.github.mune0903.githubclient.ui

import androidx.fragment.app.Fragment
import com.github.mune0903.githubclient.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by inject()


}
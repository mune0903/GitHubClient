package com.github.mune0903.githubclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.SimpleItemAnimator
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.databinding.FragmentMainBinding
import com.github.mune0903.githubclient.databinding.ItemNewsBinding
import com.github.mune0903.githubclient.util.factory.ViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainFragment : Fragment(), NewsRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMainBinding

    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(requireContext())
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val adapter = NewsRecyclerAdapter(this)

    private val newsSection = NewsSection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getNewsList("mune0903")

        viewModel.news.observe(this, Observer {
            newsSection.updateNews(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val grouupAdapter = GroupAdapter<ViewHolder>().apply {
            add(newsSection)
        }
        binding.recyclerView.apply {
            adapter = grouupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun onItemClick() {

    }

    companion object {

        val TAG: String = MainFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
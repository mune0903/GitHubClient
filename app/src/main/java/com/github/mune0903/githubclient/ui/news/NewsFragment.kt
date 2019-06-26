package com.github.mune0903.githubclient.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.databinding.FragmentNewsBinding
import com.github.mune0903.githubclient.ui.MainViewModel
import com.github.mune0903.githubclient.ui.news.item.NewsSecsion
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.android.ext.android.inject

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val mainViewModel: MainViewModel by inject()
    private val viewModel: NewsViewModel by inject()

    private val newsSection = NewsSecsion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.apply {
            getUser()
            user.observe(this@NewsFragment, Observer {
                viewModel.getNewsList(it.login.toString())
            })
        }

        viewModel.apply {
            news.observe(this@NewsFragment, Observer {
                //controller.setData(NewsItemController(it))
                newsSection.updateNews(it)
                binding.swipeRefresh.isRefreshing = false
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        binding = FragmentNewsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.swipeRefresh.isRefreshing = true
        setupRecyclerView()
        setupSwipeRefresh()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(newsSection)
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            mainViewModel.apply {
                user.observe(this@NewsFragment, Observer {
                    viewModel.getNewsList(it.login.toString())
                })
            }
        }
    }

    companion object {

        val TAG: String = NewsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}
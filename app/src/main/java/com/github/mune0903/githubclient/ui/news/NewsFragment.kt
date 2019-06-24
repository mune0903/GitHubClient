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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val mainViewModel: MainViewModel by inject()
    private val viewModel: NewsViewModel by inject()

    private val controller = NewsItemController()

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
                controller.setData(NewsItemController(it))
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
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = controller.adapter
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
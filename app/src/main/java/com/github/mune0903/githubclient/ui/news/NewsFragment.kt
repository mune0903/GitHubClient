package com.github.mune0903.githubclient.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.News
import com.github.mune0903.githubclient.data.remote.model.User
import com.github.mune0903.githubclient.databinding.FragmentNewsBinding
import com.github.mune0903.githubclient.util.factory.ViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModelFactory: ViewModelFactory by lazy {
        ViewModelFactory(requireContext())
    }

    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
    }

    private val controller = NewsItemController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            getUser()
            user.observe(this@NewsFragment, Observer {
                getNewsList(it.login.toString())
            })
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
            viewModel.getNewsList("mune0903")
        }
    }

    companion object {

        val TAG: String = NewsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}
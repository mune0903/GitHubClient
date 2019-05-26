package com.github.mune0903.githubclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mune0903.githubclient.R
import com.github.mune0903.githubclient.data.remote.model.Event
import com.github.mune0903.githubclient.databinding.FragmentMainBinding
import com.github.mune0903.githubclient.util.factory.ViewModelFactory
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    private val adapter = EventRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getEvent()
        Timber.e("here")
        viewModel.event.observe(this, Observer { event ->
            event?.let {
                val eventArray = it as ArrayList<Event>
                adapter.run {
                    userActions.clear()
                    userActions.addAll(eventArray)
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
        }
    }

    companion object {

        val TAG: String = MainFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
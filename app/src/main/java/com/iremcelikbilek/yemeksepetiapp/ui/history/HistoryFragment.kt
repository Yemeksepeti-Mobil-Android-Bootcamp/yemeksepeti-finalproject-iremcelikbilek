package com.iremcelikbilek.yemeksepetiapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.adapter.HistoryListAdapter
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentHistoryBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.gone
import com.iremcelikbilek.yemeksepetiapp.utils.show
import com.iremcelikbilek.yemeksepetiapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private var historyAdapter: HistoryListAdapter = HistoryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeHistoryList()
    }

    private fun observeHistoryList() {
        viewModel.getHistoryOrderList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    historyAdapter.setHistoryList(it.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })
    }

    private fun initViews() {
        binding.historyRv.layoutManager = LinearLayoutManager(context)
        binding.historyRv.adapter = historyAdapter
    }

    private fun showLoading() {
        binding.loadingLayout.show()
        binding.historyRv.gone()
    }

    private fun hideLoading() {
        binding.loadingLayout.gone()
        binding.historyRv.show()
    }
}
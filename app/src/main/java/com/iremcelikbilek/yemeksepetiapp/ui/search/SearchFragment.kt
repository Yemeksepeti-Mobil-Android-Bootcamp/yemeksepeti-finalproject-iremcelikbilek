package com.iremcelikbilek.yemeksepetiapp.ui.search

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.HomeRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.adapter.SearchRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentSearchBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val adapter : SearchRestaurantListAdapter = SearchRestaurantListAdapter()
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.searchToolbar)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRestaurantListRv.layoutManager = LinearLayoutManager(context)
        binding.searchRestaurantListRv.adapter = adapter

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = "Type here to search"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getRestaurantListSearchResult(newText).observe(viewLifecycleOwner, Observer {
                    when(it.status) {
                        Resource.Status.LOADING -> {

                        }

                        Resource.Status.SUCCESS -> {
                           adapter.setSearchList(it.data)
                            adapter.addListener(object: ISearchListOnClick{
                                override fun onClick(item: SearchData) {

                                }
                            })
                        }

                        Resource.Status.ERROR -> {
                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("ok") { dialog, button ->
                                    dialog.dismiss()
                                }
                            dialog.show()

                        }
                    }
                })

                return true

            }

        })

    }

}
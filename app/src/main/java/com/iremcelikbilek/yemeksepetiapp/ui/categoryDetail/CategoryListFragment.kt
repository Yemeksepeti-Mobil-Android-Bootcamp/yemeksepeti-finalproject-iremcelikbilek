package com.iremcelikbilek.yemeksepetiapp.ui.categoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.adapter.HomeRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentCategoriDetailListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.home.IRestaurantListItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment: Fragment() {

    private lateinit var binding: FragmentCategoriDetailListBinding

    private val viewModel: CategoryDetailViewModel by viewModels()

    private var adapter: HomeRestaurantListAdapter = HomeRestaurantListAdapter()
    private val args by navArgs<CategoryListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriDetailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        observeRestaurantListByCategoryId(args.categoryData.id)
    }

    private fun observeRestaurantListByCategoryId(categoryId: String?) {
        viewModel.getRestaurantList(categoryId).observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    responseCheck(it.data)
                }

                Resource.Status.ERROR -> {
                   showAlert(it.message)
                }
            }
        })
    }

    private fun responseCheck(data: RestaurantListResponse?) {
        if(data?.data == null) {
            binding.noResultTxt.show()
        }else {
            binding.noResultTxt.gone()
            adapter.setRestaurantList(data)
            adapter.addListener(object : IRestaurantListItemOnClick {
                override fun onClick(item: RestaurantData) {
                    findNavController().navigate(CategoryListFragmentDirections.actionCategoryListFragmentToMenuListFragment(item))
                }
            })
        }
    }

    private fun initViews(view: View) {
        binding.categoryNameTxt.text = args.categoryData.name
        binding.categoryRv.layoutManager = LinearLayoutManager(context)
        binding.categoryRv.adapter = adapter
    }

    private fun showLoading() {
        binding.loadingLayout.show()
        binding.categoryRv.gone()
    }

    private fun hideLoading() {
        binding.loadingLayout.gone()
        binding.categoryRv.show()
    }
}
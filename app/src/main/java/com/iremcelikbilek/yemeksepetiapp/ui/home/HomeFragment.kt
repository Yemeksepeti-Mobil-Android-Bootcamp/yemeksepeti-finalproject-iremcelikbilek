package com.iremcelikbilek.yemeksepetiapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.adapter.CategoryListAdapter
import com.iremcelikbilek.yemeksepetiapp.adapter.HomeRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryData
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.user.UserData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentHomeBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.gone
import com.iremcelikbilek.yemeksepetiapp.utils.show
import com.iremcelikbilek.yemeksepetiapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private var restaurantListAdapter: HomeRestaurantListAdapter = HomeRestaurantListAdapter()
    private var categoryListAdapter: CategoryListAdapter = CategoryListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()

        observeUser()

        observeCategoryList()

        observeRestaurantList()
    }

    private fun initRecyclerViews() {
        binding.homeRv.layoutManager = LinearLayoutManager(context)
        binding.homeRv.adapter = restaurantListAdapter

        binding.categoryRv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.categoryRv.adapter = categoryListAdapter
    }

    private fun observeRestaurantList() {
        viewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    setRestaurantData(it.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })
    }

    private fun observeCategoryList() {
        viewModel.getCategoryList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    setCategoryData(it.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })
    }

    private fun observeUser() {
        viewModel.getUser().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    binding.userNameTxt.text = ""
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    setUserData(it.data?.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })
    }

    private fun setRestaurantData(data: RestaurantListResponse?) {
        restaurantListAdapter.setRestaurantList(data)
        restaurantListAdapter.addListener(object : IRestaurantListItemOnClick {
            override fun onClick(item: RestaurantData) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMenuListFragment(item))
            }
        })
    }

    private fun setCategoryData(data: CategoryResponse?) {
        categoryListAdapter.setCategoryList(data)
        categoryListAdapter.addListener(object: ICategoryItemOnClick {
            override fun onClick(item: CategoryData) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryListFragment(item))
            }
        })
    }

    private fun setUserData(data: UserData?) {
        if(data != null) {
            binding.userNameTxt.text = "Hoşgeldin " + data.personName + " " + data.personLastName
        }else {
            binding.userNameTxt.text = "Hoşgeldin Misafir Kullanıcı"
        }
    }

    private fun showLoading() {
        binding.loadingLayout.show()
        binding.homeLayout.gone()
    }

    private fun hideLoading() {
        binding.loadingLayout.gone()
        binding.homeLayout.show()
    }

    override fun onPause() {
        super.onPause()
        restaurantListAdapter.removeListener()
    }
}
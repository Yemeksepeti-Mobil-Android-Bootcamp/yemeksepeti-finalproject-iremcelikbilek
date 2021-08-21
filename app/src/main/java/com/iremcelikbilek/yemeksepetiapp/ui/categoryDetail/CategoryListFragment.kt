package com.iremcelikbilek.yemeksepetiapp.ui.categoryDetail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.HomeRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentCategoriDetailListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.home.HomeFragmentDirections
import com.iremcelikbilek.yemeksepetiapp.ui.home.IRestaurantListItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.gone
import com.iremcelikbilek.yemeksepetiapp.utils.hide
import com.iremcelikbilek.yemeksepetiapp.utils.show
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

                    if(it.data?.data == null) {
                        binding.noResultTxt.show()
                    }else {
                        binding.noResultTxt.gone()
                        adapter.setRestaurantList(it.data)
                        adapter.addListener(object : IRestaurantListItemOnClick {
                            override fun onClick(item: RestaurantData) {
                                findNavController().navigate(CategoryListFragmentDirections.actionCategoryListFragmentToMenuListFragment(item))
                            }
                        })
                    }
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
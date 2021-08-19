package com.iremcelikbilek.yemeksepetiapp.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.HomeActivity
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.HomeRestaurantListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityData
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.databinding.ActivityMainBinding
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentHomeBinding
import com.iremcelikbilek.yemeksepetiapp.ui.citylist.ICityListItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private var restaurantListAdapter: HomeRestaurantListAdapter = HomeRestaurantListAdapter()

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

        binding.homeRv.layoutManager = LinearLayoutManager(context)
        binding.homeRv.adapter = restaurantListAdapter

        viewModel.getUser().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {

                }

                Resource.Status.SUCCESS -> {
                    it.data?.data.let { userData ->
                        if(userData != null) {
                            binding.userNameTxt.text = "Hoşgeldin " + userData.personName + " " + userData.personLastName
                        }else {
                            binding.userNameTxt.text = "Hoşgeldin Misafir Kullanıcı"
                        }
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

        viewModel.getRestaurantList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {

                }

                Resource.Status.SUCCESS -> {
                    restaurantListAdapter.setRestaurantList(it.data)
                    restaurantListAdapter.addListener(object : IRestaurantListItemOnClick {
                        override fun onClick(item: RestaurantData) {
                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMenuListFragment(item))
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
    }

    override fun onPause() {
        super.onPause()
        restaurantListAdapter.removeListener()
    }
}
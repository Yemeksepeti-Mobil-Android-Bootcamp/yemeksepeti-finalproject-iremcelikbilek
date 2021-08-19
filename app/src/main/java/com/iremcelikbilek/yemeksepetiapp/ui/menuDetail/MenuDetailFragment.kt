package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentMenuDetailBinding
import com.iremcelikbilek.yemeksepetiapp.ui.menuList.MenuListFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDetailFragment: Fragment() {

    private lateinit var binding: FragmentMenuDetailBinding
    private val args by navArgs<MenuDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuDetailNameTxt.text = args.menuItem.name
        binding.menuDetailDescriptionTxt.text = args.menuItem.description
        binding.menuPriceTxt.text = args.menuItem.price
        binding.menuDetailRestaurantName.text = args.restaurantName
    }
}
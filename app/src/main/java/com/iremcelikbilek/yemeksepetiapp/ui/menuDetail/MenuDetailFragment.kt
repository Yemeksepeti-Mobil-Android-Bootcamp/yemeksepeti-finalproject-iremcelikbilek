package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentMenuDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDetailFragment: Fragment() {

    private lateinit var binding: FragmentMenuDetailBinding
    private val args by navArgs<MenuDetailFragmentArgs>()
    private val viewModel: MenuDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        incrementMenuListener()

        removeMenuListener()
    }

    private fun initViews(view: View) {
        binding.menuDetailNameTxt.text = args.menuItem.name
        binding.menuDetailDescriptionTxt.text = args.menuItem.description
        binding.menuPriceTxt.text = args.menuItem.price
        binding.menuDetailRestaurantName.text = args.restaurantName
        Glide.with(view).load(args.menuItem.imageUrl).into(binding.menuDetailImg)
    }

    private fun removeMenuListener() {
        binding.removeBtn.setOnClickListener {
            viewModel.removeMenu()
            binding.menuCountNumberTxt.text = "${viewModel.getCounter()} adet"
        }
    }

    private fun incrementMenuListener() {
        binding.addBtn.setOnClickListener {
            viewModel.addMenu()
            binding.menuCountNumberTxt.text = "${viewModel.getCounter()} adet"
        }
    }
}
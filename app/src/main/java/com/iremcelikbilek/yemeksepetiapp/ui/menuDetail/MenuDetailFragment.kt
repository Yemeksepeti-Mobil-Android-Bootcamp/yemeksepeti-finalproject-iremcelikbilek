package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentMenuDetailBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.showAlert
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

        addCartBtnListener()
    }


    private fun initViews(view: View) {
        binding.menuDetailNameTxt.text = args.menuItem.name
        binding.menuDetailDescriptionTxt.text = args.menuItem.description
        binding.menuPriceTxt.text = args.menuItem.price
        binding.menuDetailRestaurantName.text = args.restaurantName
        Glide.with(view).load(args.menuItem.imageUrl).placeholder(R.drawable.loading).error(R.drawable.not_found).into(binding.menuDetailImg)
    }

    private fun removeMenuListener() {
        binding.removeBtn.setOnClickListener {
            viewModel.removeMenu()
            binding.menuCountNumberTxt.text = "${viewModel.getCounter()} adet"
            binding.menuPriceTxt.text = viewModel.calculatePrice(args.menuItem.price)
        }
    }

    private fun incrementMenuListener() {
        binding.addBtn.setOnClickListener {
            viewModel.addMenu()
            binding.menuCountNumberTxt.text = "${viewModel.getCounter()} adet"
            binding.menuPriceTxt.text = viewModel.calculatePrice(args.menuItem.price)
        }
    }

    private fun addCartBtnListener() {
        binding.addCartBtn.setOnClickListener {
            viewModel.addCartData(args.restaurantId, args.menuItem.id).observe(viewLifecycleOwner, Observer {
                when(it.status) {
                    Resource.Status.LOADING -> {

                    }

                    Resource.Status.SUCCESS -> {
                       findNavController().navigate(MenuDetailFragmentDirections.actionMenuDetailFragmentToCartFragment())
                    }

                    Resource.Status.ERROR -> {
                        showAlert(it.message)
                    }
                }
            })
        }
    }

}
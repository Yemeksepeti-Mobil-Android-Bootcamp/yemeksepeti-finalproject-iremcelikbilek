package com.iremcelikbilek.yemeksepetiapp.ui.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.adapter.CartListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentCartBinding
import com.iremcelikbilek.yemeksepetiapp.ui.home.HomeFragmentDirections
import com.iremcelikbilek.yemeksepetiapp.ui.home.ICategoryItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()

    private var cartAdapter : CartListAdapter = CartListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeCart()
    }

    private fun observeCart() {
        viewModel.getCartList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {

                }

                Resource.Status.SUCCESS -> {
                    cartAdapter.setCartList(it.data)
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

    private fun initViews() {
        binding.cartRv.layoutManager = LinearLayoutManager(context)
        binding.cartRv.adapter = cartAdapter
    }
}
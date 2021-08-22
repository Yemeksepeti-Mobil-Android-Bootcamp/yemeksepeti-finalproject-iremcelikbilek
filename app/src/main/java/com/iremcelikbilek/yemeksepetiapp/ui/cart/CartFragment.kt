package com.iremcelikbilek.yemeksepetiapp.ui.cart

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.CartListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentCartBinding
import com.iremcelikbilek.yemeksepetiapp.utils.*
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

        cartAdapterListener()

        completeOrderBtnListener()
    }

    private fun cartAdapterListener() {
        cartAdapter.addListener(object: ICartItemOnClick {
            override fun onClick(item: CartData) {
                viewModel.removeCartData(item.id, item.menu.id).observe(viewLifecycleOwner, Observer { response ->
                    when(response.status) {
                        Resource.Status.LOADING -> {

                        }

                        Resource.Status.SUCCESS -> {
                            observeCart()
                        }

                        Resource.Status.ERROR -> {
                           showAlert(response.message)
                        }
                    }

                })
            }

        })
    }

    private fun completeOrderBtnListener() {
        binding.completeOrderBtn.setOnClickListener {
            viewModel.completeOrder().observe(viewLifecycleOwner, Observer {
                when(it.status) {
                    Resource.Status.LOADING -> {

                    }

                    Resource.Status.SUCCESS -> {
                        showDialog()
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

    }

    private fun observeCart() {
        viewModel.getCartList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    hideCartLayout()
                    binding.loadingLayout.show()
                }

                Resource.Status.SUCCESS -> {
                    showCartLayout()
                    binding.loadingLayout.gone()

                    cartAdapter.setCartList(it.data)
                    binding.totalPriceTxt.text = viewModel.calculatePrice(it.data?.data)

                }

                Resource.Status.ERROR -> {
                    binding.noCartLayout.show()
                    binding.loadingLayout.gone()
                    hideCartLayout()
                }
            }
        })
    }

    private fun hideCartLayout() {
        binding.cartRv.gone()
        binding.totalPriceLayout.gone()
        binding.completeOrderBtn.gone()
    }

    private fun showCartLayout() {
        binding.cartRv.show()
        binding.totalPriceLayout.show()
        binding.completeOrderBtn.show()
    }


    private fun initViews() {
        binding.cartRv.layoutManager = LinearLayoutManager(context)
        binding.cartRv.adapter = cartAdapter
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_view)
        val yesBtn = dialog.findViewById(R.id.done_btn) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment())
        }
        dialog.show()
    }
}
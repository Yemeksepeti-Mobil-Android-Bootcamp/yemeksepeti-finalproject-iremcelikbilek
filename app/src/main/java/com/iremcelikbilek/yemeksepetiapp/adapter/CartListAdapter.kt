package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemCartListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.cart.ICartItemOnClick

class CartListAdapter: RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

    private var cartList: CartListResponse? = null
    private var listener: ICartItemOnClick? = null

    fun setCartList(cartList: CartListResponse?) {
        this.cartList = cartList
        notifyDataSetChanged()
    }

    fun getCartList() = cartList

    fun addListener(listener: ICartItemOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class CartListViewHolder(var binding: ItemCartListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CartData) {
            Glide.with(binding.root.context).load(item.menu.imageUrl).into(binding.menuImg)
            binding.restaurantNameTxt.text = item.name
            binding.menuNameTxt.text = item.menu.name
            binding.priceTxt.text = item.menu.price

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val view = ItemCartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        holder.setItem(cartList?.data!![position])
    }

    override fun getItemCount(): Int = cartList?.data?.size ?: 0

}
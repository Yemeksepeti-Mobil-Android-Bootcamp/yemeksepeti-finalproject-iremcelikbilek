package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemCartListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.cart.ICartItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import com.iremcelikbilek.yemeksepetiapp.utils.loadFromUrl

class CartListAdapter: RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {
    private var cartList: CartListResponse? = null
    private var listener: ICartItemOnClick? = null

    fun setCartList(cartList: CartListResponse?) {
        this.cartList = cartList
        notifyDataSetChanged()
    }

    fun addListener(listener: ICartItemOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class CartListViewHolder(var binding: ItemCartListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CartData, listener: ICartItemOnClick?) {
            binding.menuImg.loadFromUrl(item.menu.imageUrl)
            binding.restaurantNameTxt.text = item.name
            binding.menuNameTxt.text = item.menu.name
            binding.priceTxt.text = item.menu.price
            binding.removeBtn.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val view = ItemCartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        ifNotNull(cartList?.data?.elementAtOrNull(position)) {
            holder.setItem(it, listener)
        }
    }

    override fun getItemCount(): Int = cartList?.data?.size ?: 0
}
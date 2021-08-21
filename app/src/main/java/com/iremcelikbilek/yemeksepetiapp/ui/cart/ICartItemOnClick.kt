package com.iremcelikbilek.yemeksepetiapp.ui.cart

import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData

interface ICartItemOnClick {
    fun onClick(item: CartData)
}
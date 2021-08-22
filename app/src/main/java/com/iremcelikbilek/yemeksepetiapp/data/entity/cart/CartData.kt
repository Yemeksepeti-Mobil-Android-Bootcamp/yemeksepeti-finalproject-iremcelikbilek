package com.iremcelikbilek.yemeksepetiapp.data.entity.cart

import com.google.gson.annotations.SerializedName
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Menu

data class CartData(
    @SerializedName("id")
    val id: String,
    @SerializedName("menu")
    val menu: Menu,
    @SerializedName("name")
    val name: String
)
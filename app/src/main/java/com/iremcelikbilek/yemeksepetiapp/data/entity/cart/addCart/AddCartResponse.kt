package com.iremcelikbilek.yemeksepetiapp.data.entity.cart.addCart


import com.google.gson.annotations.SerializedName

data class AddCartResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
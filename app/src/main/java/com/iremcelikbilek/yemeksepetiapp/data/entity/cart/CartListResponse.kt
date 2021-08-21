package com.iremcelikbilek.yemeksepetiapp.data.entity.cart


import com.google.gson.annotations.SerializedName

data class CartListResponse(
    @SerializedName("data")
    val `data`: List<CartData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
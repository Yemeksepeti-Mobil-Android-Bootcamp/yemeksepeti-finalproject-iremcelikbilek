package com.iremcelikbilek.yemeksepetiapp.data.entity.history


import com.google.gson.annotations.SerializedName
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData

data class HistoryResponse(
    @SerializedName("data")
    val `data`: List<CartData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
package com.iremcelikbilek.yemeksepetiapp.data.entity.cart.completeOrder


import com.google.gson.annotations.SerializedName

data class CompleteOrderResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
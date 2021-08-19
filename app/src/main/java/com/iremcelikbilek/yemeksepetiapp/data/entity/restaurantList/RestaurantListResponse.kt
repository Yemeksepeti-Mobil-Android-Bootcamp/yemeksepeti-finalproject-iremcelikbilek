package com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList


import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(
    @SerializedName("data")
    val `data`: List<RestaurantData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
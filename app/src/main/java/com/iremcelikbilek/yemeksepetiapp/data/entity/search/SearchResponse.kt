package com.iremcelikbilek.yemeksepetiapp.data.entity.search


import com.google.gson.annotations.SerializedName
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData

data class SearchResponse(
    @SerializedName("data")
    val `data`: List<RestaurantData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
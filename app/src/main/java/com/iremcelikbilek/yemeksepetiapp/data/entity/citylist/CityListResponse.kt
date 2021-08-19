package com.iremcelikbilek.yemeksepetiapp.data.entity.citylist


import com.google.gson.annotations.SerializedName

data class CityListResponse(
    @SerializedName("data")
    val `data`: List<CityData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
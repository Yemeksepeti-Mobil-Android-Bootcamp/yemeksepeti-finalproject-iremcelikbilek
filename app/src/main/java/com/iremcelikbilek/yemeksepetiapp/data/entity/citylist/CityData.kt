package com.iremcelikbilek.yemeksepetiapp.data.entity.citylist


import com.google.gson.annotations.SerializedName

data class CityData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
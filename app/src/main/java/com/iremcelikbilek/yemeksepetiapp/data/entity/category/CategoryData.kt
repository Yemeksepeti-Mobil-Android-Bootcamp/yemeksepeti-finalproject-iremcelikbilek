package com.iremcelikbilek.yemeksepetiapp.data.entity.category


import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
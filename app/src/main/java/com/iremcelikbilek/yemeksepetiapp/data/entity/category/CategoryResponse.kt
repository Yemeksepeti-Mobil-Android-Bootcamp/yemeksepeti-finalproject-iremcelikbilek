package com.iremcelikbilek.yemeksepetiapp.data.entity.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<CategoryData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
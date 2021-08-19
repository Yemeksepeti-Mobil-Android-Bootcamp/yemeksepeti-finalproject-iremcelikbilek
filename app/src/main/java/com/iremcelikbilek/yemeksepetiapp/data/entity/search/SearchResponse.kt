package com.iremcelikbilek.yemeksepetiapp.data.entity.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val `data`: List<SearchData>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
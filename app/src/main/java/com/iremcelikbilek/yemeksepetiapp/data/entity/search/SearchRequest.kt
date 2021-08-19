package com.iremcelikbilek.yemeksepetiapp.data.entity.search


import com.google.gson.annotations.SerializedName

data class SearchRequest(
    @SerializedName("search")
    val search: String?
)
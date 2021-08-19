package com.iremcelikbilek.yemeksepetiapp.data.entity.common


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("expires")
    val expires: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)
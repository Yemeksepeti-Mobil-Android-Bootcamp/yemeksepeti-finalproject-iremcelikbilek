package com.iremcelikbilek.yemeksepetiapp.data.entity.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val `data`: UserData,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
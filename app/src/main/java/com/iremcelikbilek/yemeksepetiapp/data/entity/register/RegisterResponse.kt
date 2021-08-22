package com.iremcelikbilek.yemeksepetiapp.data.entity.register


import com.google.gson.annotations.SerializedName
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Data

data class RegisterResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)
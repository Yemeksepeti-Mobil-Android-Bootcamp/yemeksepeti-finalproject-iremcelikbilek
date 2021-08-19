package com.iremcelikbilek.yemeksepetiapp.data.entity.login


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("personEmail")
    val personEmail: String,
    @SerializedName("password")
    val password: String
)
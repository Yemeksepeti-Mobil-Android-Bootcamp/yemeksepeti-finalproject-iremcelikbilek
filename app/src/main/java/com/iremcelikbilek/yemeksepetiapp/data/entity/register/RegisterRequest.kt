package com.iremcelikbilek.yemeksepetiapp.data.entity.register


import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("personName")
    val personName: String,
    @SerializedName("personLastName")
    val personLastName: String,
    @SerializedName("personPhone")
    val personPhone: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("personEmail")
    val personEmail: String
)
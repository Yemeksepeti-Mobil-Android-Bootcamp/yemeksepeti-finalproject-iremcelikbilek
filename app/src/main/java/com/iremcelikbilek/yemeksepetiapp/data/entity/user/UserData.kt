package com.iremcelikbilek.yemeksepetiapp.data.entity.user


import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("password")
    val password: String,
    @SerializedName("personEmail")
    val personEmail: String,
    @SerializedName("personLastName")
    val personLastName: String,
    @SerializedName("personName")
    val personName: String,
    @SerializedName("personPhone")
    val personPhone: String,
    @SerializedName("signInDate")
    val signInDate: String,
    @SerializedName("signUpDate")
    val signUpDate: String
)
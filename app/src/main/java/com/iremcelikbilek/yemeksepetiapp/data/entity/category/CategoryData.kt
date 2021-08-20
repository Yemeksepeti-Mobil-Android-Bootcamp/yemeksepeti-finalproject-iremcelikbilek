package com.iremcelikbilek.yemeksepetiapp.data.entity.category


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
): Parcelable
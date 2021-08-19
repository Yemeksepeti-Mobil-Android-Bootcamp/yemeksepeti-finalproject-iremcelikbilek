package com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Menu
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantData(
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("estimated_arrival_time")
    val estimatedArrivalTime: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("menu")
    val menu: List<Menu>,
    @SerializedName("minimum_price")
    val minimumPrice: String,
    @SerializedName("name")
    val name: String
): Parcelable
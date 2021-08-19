package com.iremcelikbilek.yemeksepetiapp.ui.home

import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantData

interface IRestaurantListItemOnClick {
    fun onClick(item: RestaurantData)
}
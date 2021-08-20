package com.iremcelikbilek.yemeksepetiapp.ui.home

import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData

interface IRestaurantListItemOnClick {
    fun onClick(item: RestaurantData)
}
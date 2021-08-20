package com.iremcelikbilek.yemeksepetiapp.ui.search

import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData

interface ISearchListOnClick {
    fun onClick(item: RestaurantData)
}
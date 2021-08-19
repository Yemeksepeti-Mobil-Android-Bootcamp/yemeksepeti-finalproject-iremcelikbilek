package com.iremcelikbilek.yemeksepetiapp.ui.citylist

import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityData

interface ICityListItemOnClick {
    fun onClick(item: CityData)
}
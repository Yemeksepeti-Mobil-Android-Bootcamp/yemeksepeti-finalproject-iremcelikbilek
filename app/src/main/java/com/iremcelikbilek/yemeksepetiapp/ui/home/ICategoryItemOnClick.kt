package com.iremcelikbilek.yemeksepetiapp.ui.home

import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryData

interface ICategoryItemOnClick {
    fun onClick(item: CategoryData)
}
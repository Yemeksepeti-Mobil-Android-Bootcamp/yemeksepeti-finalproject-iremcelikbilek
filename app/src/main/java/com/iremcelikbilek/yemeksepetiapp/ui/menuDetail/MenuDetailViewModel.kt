package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import androidx.lifecycle.ViewModel

class MenuDetailViewModel: ViewModel() {
    private var foodCounter: Int = 1

    fun getCounter() : Int = foodCounter

    fun addMenu() {
        this.foodCounter++
    }

    fun removeMenu() {
        if(foodCounter <= 1) return
        this.foodCounter--
    }

}
package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.addCart.AddCartResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    
    private var foodCounter: Int = 1

    fun addCartData(restaurantId: String?, menuId: String?): LiveData<Resource<AddCartResponse>> {
        return foodApiRepository.addCartData(getToken(), restaurantId, menuId, foodCounter)
    }

    private fun getToken(): String? = foodApiRepository.checkToken()

    fun getCounter() : Int = foodCounter

    fun addMenu() {
        this.foodCounter++
    }

    fun removeMenu() {
        if(foodCounter <= 1) return
        this.foodCounter--
    }

    fun calculatePrice(price: String?) : String? {
        val totalPrice = price?.substringBefore("TL")?.toDouble()
        return (totalPrice!! * foodCounter).toString()
    }
}
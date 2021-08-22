package com.iremcelikbilek.yemeksepetiapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.completeOrder.CompleteOrderResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.removeCart.RemoveCartResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun completeOrder(): LiveData<Resource<CompleteOrderResponse>> {
        return foodApiRepository.completeOrder(getToken())
    }

    fun getCartList(): LiveData<Resource<CartListResponse>> {
        return foodApiRepository.getCartList(getToken())
    }

    fun removeCartData(restaurantId: String?, menuId: String?):  LiveData<Resource<RemoveCartResponse>> {
        return foodApiRepository.removeToCart(getToken(), restaurantId, menuId)
    }

    fun getToken(): String? = foodApiRepository.checkToken()

    fun calculatePrice(cardDataList: List<CartData>?): String {
        var totalPrice: Double = 0.0
        cardDataList?.forEach { cartData ->
            totalPrice += cartData.menu.price.substringBefore("TL").toDouble()
        }

        return String.format("%.2f", totalPrice)
    }
}
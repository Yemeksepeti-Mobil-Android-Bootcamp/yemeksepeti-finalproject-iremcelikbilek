package com.iremcelikbilek.yemeksepetiapp.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartListResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {

    fun getCartList(): LiveData<Resource<CartListResponse>> {
        return foodApiRepository.getCartList(getToken())
    }

    private fun getToken(): String? = foodApiRepository.checkToken()
}
package com.iremcelikbilek.yemeksepetiapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
) : ViewModel() {
    fun getRestaurantList(categoryId: String? = null): LiveData<Resource<RestaurantListResponse>> = foodApiRepository.getRestaurantList(getCity(), categoryId)

    fun getCategoryList() : LiveData<Resource<CategoryResponse>> = foodApiRepository.getCategoryList()

    fun getUser() = foodApiRepository.getUser(getToken())

    private fun getToken() = foodApiRepository.checkToken()

    private fun getCity(): String? = foodApiRepository.getCity()
}
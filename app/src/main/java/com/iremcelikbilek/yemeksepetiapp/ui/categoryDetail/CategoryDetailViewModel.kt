package com.iremcelikbilek.yemeksepetiapp.ui.categoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun getRestaurantList(categoryId: String?): LiveData<Resource<RestaurantListResponse>> = foodApiRepository.getRestaurantList(getCity(), categoryId)

    private fun getCity(): String? = foodApiRepository.getCity()
}
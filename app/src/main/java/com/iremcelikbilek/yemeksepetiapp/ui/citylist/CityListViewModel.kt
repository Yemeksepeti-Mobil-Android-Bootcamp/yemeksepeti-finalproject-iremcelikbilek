package com.iremcelikbilek.yemeksepetiapp.ui.citylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityListResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun getCityList(): LiveData<Resource<CityListResponse>> = foodApiRepository.getCityList()

    fun saveCity(cityId: String) {
        foodApiRepository.saveCity(cityId)
    }
}

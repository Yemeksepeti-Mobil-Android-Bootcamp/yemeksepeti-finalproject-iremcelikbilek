package com.iremcelikbilek.yemeksepetiapp.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun checkToken(): String? = foodApiRepository.checkToken()
}
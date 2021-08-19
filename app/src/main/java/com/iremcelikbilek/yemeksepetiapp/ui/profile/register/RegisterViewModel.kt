package com.iremcelikbilek.yemeksepetiapp.ui.profile.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
) : ViewModel() {

    fun register(name: String, lastname: String, phone: String, email: String, password: String): LiveData<Resource<RegisterResponse>> {
        val registerRequest = RegisterRequest(name, lastname, phone, email, password)
        return foodApiRepository.register(registerRequest)
    }
}
package com.iremcelikbilek.yemeksepetiapp.ui.profile.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val foodApiRepository: FoodApiRepository
): ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<LoginResponse>> {
        val loginRequest = LoginRequest(email, password)
        return foodApiRepository.login(loginRequest)
    }
}
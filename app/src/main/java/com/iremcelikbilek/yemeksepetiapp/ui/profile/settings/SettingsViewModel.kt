package com.iremcelikbilek.yemeksepetiapp.ui.profile.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun getUser() = foodApiRepository.getUser(getToken())

    fun logout() = foodApiRepository.logout()

    private fun getToken() = foodApiRepository.checkToken()
}
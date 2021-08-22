package com.iremcelikbilek.yemeksepetiapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.iremcelikbilek.yemeksepetiapp.data.FoodApiRepository
import com.iremcelikbilek.yemeksepetiapp.data.entity.history.HistoryResponse
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodApiRepository: FoodApiRepository
): ViewModel() {
    fun getHistoryOrderList(): LiveData<Resource<HistoryResponse>> {
        return foodApiRepository.getHistoryOrderList(getToken())
    }

    private fun getToken(): String? = foodApiRepository.checkToken()
}
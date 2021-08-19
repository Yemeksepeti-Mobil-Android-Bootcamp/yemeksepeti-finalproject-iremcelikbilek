package com.iremcelikbilek.yemeksepetiapp.data.remote

import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchRequest
import com.iremcelikbilek.yemeksepetiapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodApiService: FoodApiService
): BaseDataSource() {

    suspend fun register(request: RegisterRequest) = getResult { foodApiService.register(request) }

    suspend fun login(request: LoginRequest) = getResult { foodApiService.login(request) }

    suspend fun getCityList() = getResult { foodApiService.getCityList() }

    suspend fun getRestaurantList(cityId: Int?, categoryId: Int?) = getResult { foodApiService.getRestaurantList(cityId, categoryId) }

    suspend fun getUser(token: String?) = getResult { foodApiService.getUser(token) }

    suspend fun getRestaurantListSearchResult(request: SearchRequest) = getResult { foodApiService.getRestaurantListSearchResult(request) }
}
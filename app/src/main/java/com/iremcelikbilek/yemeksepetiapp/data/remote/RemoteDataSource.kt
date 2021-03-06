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

    suspend fun getRestaurantList(cityId: String?, categoryId: String?) = getResult { foodApiService.getRestaurantList(cityId, categoryId) }

    suspend fun getUser(token: String?) = getResult { foodApiService.getUser(token) }

    suspend fun getRestaurantListSearchResult(request: SearchRequest) = getResult { foodApiService.getRestaurantListSearchResult(request) }

    suspend fun getCategoryList() = getResult { foodApiService.getCategoryList() }

    suspend fun getCartList(token: String?) = getResult { foodApiService.getCartList(token) }

    suspend fun addCartData(token: String?, restaurantId: String?, menuId: String?, count: Int?) = getResult { foodApiService.addCartData(token, restaurantId, menuId, count) }

    suspend fun removeToCart(token: String?, restaurantId: String?, menuId: String?) = getResult { foodApiService.removeToCart(token, restaurantId, menuId) }

    suspend fun completeOrder(token: String?) = getResult { foodApiService.completeOrder(token) }

    suspend fun getHistoryOrderList(token: String?) = getResult { foodApiService.getHistoryOrderList(token) }
}
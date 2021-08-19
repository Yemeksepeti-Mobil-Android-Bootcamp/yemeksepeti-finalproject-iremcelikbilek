package com.iremcelikbilek.yemeksepetiapp.data

import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchRequest
import com.iremcelikbilek.yemeksepetiapp.data.locale.LocaleDataSource
import com.iremcelikbilek.yemeksepetiapp.data.remote.RemoteDataSource
import com.iremcelikbilek.yemeksepetiapp.utils.performAuthTokenNetworkOperation
import com.iremcelikbilek.yemeksepetiapp.utils.performNetworkOperation
import javax.inject.Inject

class FoodApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localeDataSource: LocaleDataSource
) {

    fun register(registerRequest: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.register(registerRequest)
        },

        save = {
            localeDataSource.saveToken(it)
        }
    )

    fun login(loginRequest: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.login(loginRequest)
        },

        save = {
            localeDataSource.saveToken(it)
        }
    )

    fun checkToken(): String? {
        return localeDataSource.getToken()
    }

    fun getCityList() = performNetworkOperation { remoteDataSource.getCityList() }

    fun saveCity(cityId: String) {
        localeDataSource.saveCity(cityId)
    }

    fun getCity(): String? {
        return localeDataSource.getCity()
    }

    fun getRestaurantList(cityId: Int?, categoryId: Int?) = performNetworkOperation { remoteDataSource.getRestaurantList(cityId, categoryId) }

    fun getUser(token: String?) = performNetworkOperation { remoteDataSource.getUser(token) }

    fun getRestaurantListSearchResult(searchRequest: SearchRequest) = performNetworkOperation { remoteDataSource.getRestaurantListSearchResult(searchRequest) }
}
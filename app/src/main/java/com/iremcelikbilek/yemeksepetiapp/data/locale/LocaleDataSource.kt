package com.iremcelikbilek.yemeksepetiapp.data.locale

import javax.inject.Inject

class LocaleDataSource @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun saveToken(token: String) {
        sharedPreferencesManager.saveToken(token)
    }

    fun getToken(): String? {
        return sharedPreferencesManager.getToken()
    }

    fun saveCity(cityId: String) {
        sharedPreferencesManager.saveCity(cityId)
    }

    fun getCity(): String? {
        return sharedPreferencesManager.getCity()
    }
}
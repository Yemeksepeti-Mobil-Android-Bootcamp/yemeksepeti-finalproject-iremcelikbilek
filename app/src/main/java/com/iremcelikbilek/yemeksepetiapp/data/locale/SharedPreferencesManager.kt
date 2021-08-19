package com.iremcelikbilek.yemeksepetiapp.data.locale

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    companion object {
        const val TOKEN = "com.iremcelikbilek.yemeksepetiapp.TOKEN"
        const val CITY_KEY = "CityId"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("sharedPreferencesManager", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun saveCity(cityId: String) {
        sharedPreferences.edit().putString(CITY_KEY, cityId).apply()
    }

    fun getCity(): String? {
        return sharedPreferences.getString(CITY_KEY, "")
    }
}
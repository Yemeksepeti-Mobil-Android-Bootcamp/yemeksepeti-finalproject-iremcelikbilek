package com.iremcelikbilek.yemeksepetiapp.data.remote

import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.login.LoginResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.register.RegisterResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchRequest
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.user.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface FoodApiService {

    @POST("signup")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("signin")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("cityList")
    suspend fun getCityList(): Response<CityListResponse>

    @GET("restaurantListing")
    suspend fun getRestaurantList(@Query("city") cityId: Int?, @Query("category") categoryId: Int?): Response<RestaurantListResponse>

    @GET("user")
    suspend fun getUser(@Header("token") token: String?) : Response<UserResponse>

    @POST("search")
    suspend fun getRestaurantListSearchResult(@Body request: SearchRequest) : Response<SearchResponse>
}
package com.iremcelikbilek.yemeksepetiapp.data.remote

import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.addCart.AddCartResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.completeOrder.CompleteOrderResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.removeCart.RemoveCartResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityListResponse
import com.iremcelikbilek.yemeksepetiapp.data.entity.history.HistoryResponse
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
    suspend fun getRestaurantList(@Query("city") cityId: String?, @Query("category") categoryId: String?): Response<RestaurantListResponse>

    @GET("user")
    suspend fun getUser(@Header("token") token: String?) : Response<UserResponse>

    @POST("search")
    suspend fun getRestaurantListSearchResult(@Body request: SearchRequest) : Response<SearchResponse>

    @GET("categoryList")
    suspend fun getCategoryList() : Response<CategoryResponse>

    @GET("basket")
    suspend fun getCartList(@Header("token") token: String?) : Response<CartListResponse>

    @GET("addToBasket")
    suspend fun addCartData(
        @Header("token") token: String?,
        @Query("restaurant") restaurantId: String?,
        @Query("menu") menuId: String?,
        @Query("count") count: Int?
    ): Response<AddCartResponse>

    @GET("removeToBasket")
    suspend fun removeToCart(
        @Header("token") token: String?,
        @Query("restaurant") restaurantId: String?,
        @Query("menu") menuId: String?
    ): Response<RemoveCartResponse>

    @GET("checkout")
    suspend fun completeOrder(@Header("token") token: String?) : Response<CompleteOrderResponse>

    @GET("history")
    suspend fun getHistoryOrderList(@Header("token") token: String?) : Response<HistoryResponse>

}
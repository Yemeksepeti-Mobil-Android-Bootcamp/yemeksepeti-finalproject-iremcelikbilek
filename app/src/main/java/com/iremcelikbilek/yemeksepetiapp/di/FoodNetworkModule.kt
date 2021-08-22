package com.iremcelikbilek.yemeksepetiapp.di

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Endpoint
import com.iremcelikbilek.yemeksepetiapp.data.remote.FoodApiService
import com.iremcelikbilek.yemeksepetiapp.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class FoodNetworkModule {
    @Provides
    fun provideRemoteDataSource(
        apiService: FoodApiService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideFoodApiService(retrofit: Retrofit): FoodApiService {
        return retrofit.create(FoodApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endpoint: Endpoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideEndpoint(): Endpoint {
        return Endpoint("https://yemeksepeti.iremcelikbilek.workers.dev/")
    }
}
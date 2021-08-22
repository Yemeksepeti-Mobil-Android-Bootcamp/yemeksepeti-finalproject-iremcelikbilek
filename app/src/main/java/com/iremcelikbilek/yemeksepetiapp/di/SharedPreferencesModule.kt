package com.iremcelikbilek.yemeksepetiapp.di

import android.content.Context
import com.iremcelikbilek.yemeksepetiapp.data.locale.LocaleDataSource
import com.iremcelikbilek.yemeksepetiapp.data.locale.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class SharedPreferencesModule {
    @Provides
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    fun provideLocalDataSource(sharedPreferencesManager: SharedPreferencesManager): LocaleDataSource {
        return LocaleDataSource(sharedPreferencesManager)
    }
}
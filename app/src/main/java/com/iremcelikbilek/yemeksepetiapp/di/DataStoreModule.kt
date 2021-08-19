package com.iremcelikbilek.yemeksepetiapp.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataStoreModule {

   /* @Provides
    fun providePreferences(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }*/

     @Provides
    fun providePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(
            name = "com.iremcelikbilek.yemeksepetiapp.di"
        )
    }

   /* @Provides
    fun localDataSource(dataStoreManager: DataStoreManager): LocaleDataSource {
        return LocaleDataSource(dataStoreManager)
    }*/
}
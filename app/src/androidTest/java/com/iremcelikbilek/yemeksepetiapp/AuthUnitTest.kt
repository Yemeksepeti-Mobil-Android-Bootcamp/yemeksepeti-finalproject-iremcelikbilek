package com.iremcelikbilek.yemeksepetiapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iremcelikbilek.yemeksepetiapp.data.locale.SharedPreferencesManager
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class AuthUnitTest {
    @Test
    fun test_saveToken() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val manager = SharedPreferencesManager(appContext)
        manager.saveToken("TOKEN")

        assertEquals("TOKEN", manager.getToken())
    }

    @Test
    fun test_saveCity() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val manager = SharedPreferencesManager(appContext)
        manager.saveCity("35")

        assertEquals("35", manager.getCity())
    }
}
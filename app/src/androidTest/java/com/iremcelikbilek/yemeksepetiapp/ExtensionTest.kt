package com.iremcelikbilek.yemeksepetiapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExtensionTest {
    @Test
    fun test_nullCheck_nullState() {
        val nullObject: String? = null
        ifNotNull(nullObject) {
            assertTrue(false)
        }
    }

    @Test
    fun test_nullCheck_notNull() {
        val nonNullObject: String = "Hello"

        ifNotNull(nonNullObject) {
            assertTrue(true)
        }
    }
}
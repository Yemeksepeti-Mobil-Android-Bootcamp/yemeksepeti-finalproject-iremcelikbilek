package com.iremcelikbilek.yemeksepetiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.iremcelikbilek.yemeksepetiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //binding?.bottomNavView?.setupWithNavController(findNavController(R.id.fragmentContainerView))
    }
}

/*<com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_nav_view"
        android:layout_width="match_parent"
        android:layout_height="?actionBarSize"
        android:background="@color/primaryColor"
        app:itemIconTint="@color/accentColor"
        app:itemTextColor="@color/accentColor"
        app:labelVisibilityMode="labeled"
        app:menu="@menu/bottom_nav_menu"
        android:layout_gravity="bottom"/>*/
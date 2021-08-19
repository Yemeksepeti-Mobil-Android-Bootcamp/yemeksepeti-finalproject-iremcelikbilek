package com.iremcelikbilek.yemeksepetiapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iremcelikbilek.yemeksepetiapp.ui.profile.login.LoginFragment
import com.iremcelikbilek.yemeksepetiapp.ui.profile.register.RegisterFragment

private const val FRAGMENT_COUNT = 2

class ProfileAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RegisterFragment()
            1 -> LoginFragment()
            else -> RegisterFragment()
        }
    }
}
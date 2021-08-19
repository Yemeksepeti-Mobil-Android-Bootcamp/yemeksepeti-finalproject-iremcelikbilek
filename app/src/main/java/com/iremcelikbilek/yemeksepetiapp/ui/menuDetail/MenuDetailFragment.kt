package com.iremcelikbilek.yemeksepetiapp.ui.menuDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentMenuDetailBinding

class MenuDetailFragment: Fragment() {

    private lateinit var binding: FragmentMenuDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
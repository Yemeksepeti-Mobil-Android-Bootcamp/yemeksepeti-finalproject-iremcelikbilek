package com.iremcelikbilek.yemeksepetiapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.ProfileAdapter
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var adapter: ProfileAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkToken()

        setAdapter()
    }

    private fun setAdapter() {
        adapter = ProfileAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.profileViewPager.adapter = adapter
        TabLayoutMediator(binding.profileTabLayout, binding.profileViewPager) {tab, position ->
            when(position) {
                0 -> tab.text = "Register"
                1 -> tab.text = "Login"
            }
        }.attach()
    }

    private fun checkToken() {
        viewModel.checkToken().let {
            if(it != null && it != "") {
                findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
            }
        }
    }
}
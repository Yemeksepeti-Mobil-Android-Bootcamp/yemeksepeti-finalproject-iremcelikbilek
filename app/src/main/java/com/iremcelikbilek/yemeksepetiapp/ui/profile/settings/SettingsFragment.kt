package com.iremcelikbilek.yemeksepetiapp.ui.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.iremcelikbilek.yemeksepetiapp.data.entity.user.UserData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentSettingsBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.gone
import com.iremcelikbilek.yemeksepetiapp.utils.show
import com.iremcelikbilek.yemeksepetiapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUser()

        historyLayoutListener()
    }

    private fun historyLayoutListener() {
        binding.historyLayout.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToHistoryFragment())
        }
    }

    private fun observeUser() {
        viewModel.getUser().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }

                Resource.Status.SUCCESS -> {
                    hideLoading()
                    setUserData(it.data?.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })
    }

    private fun setUserData(data: UserData?) {
        binding.fullNameTxt.text = data?.personName + " " + data?.personLastName
        binding.emailTxt.text = data?.personEmail
        binding.phoneTxt.text = data?.personPhone
    }

    private fun showLoading() {
        binding.loadingLayout.show()
        binding.settingsLayout.gone()
    }

    private fun hideLoading() {
        binding.loadingLayout.gone()
        binding.settingsLayout.show()
    }
}
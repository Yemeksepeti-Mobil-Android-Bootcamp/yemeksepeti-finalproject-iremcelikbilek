package com.iremcelikbilek.yemeksepetiapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.iremcelikbilek.yemeksepetiapp.HomeActivity
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment: Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel : SplashViewModel by viewModels()
    @Inject lateinit var preferences: DataStore<Preferences>
    private val handler = Handler()
    private val runnable = Runnable {
        lifecycleScope.launch {
            preferences.data.collectLatest {
                if(it[preferencesKey<Boolean>("oneTime")] == true) {
                    if(viewModel.getCity() == "") {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToCityListFragment())
                    }else {
                        startActivity(Intent(context, HomeActivity::class.java))
                        requireActivity().finish()
                    }
                } else {
                    requireView().findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
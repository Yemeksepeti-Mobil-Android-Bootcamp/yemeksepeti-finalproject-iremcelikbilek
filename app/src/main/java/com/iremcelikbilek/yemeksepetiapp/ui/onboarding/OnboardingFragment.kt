package com.iremcelikbilek.yemeksepetiapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.adapter.OnboardingAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.onboarding.OnboardingData
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFragment: Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    @Inject lateinit var preferences: DataStore<Preferences>

    private val onboardingAdapter = OnboardingAdapter(
        listOf(
            OnboardingData("Order Meal", "Browse the menu \n and order directly from \n the application", "onboarding1.json"),
            OnboardingData("Speed Delivery", "Your order will be \n immediately collected and \n sent by our courier", "onboarding2.json"),
            OnboardingData("Enjoy Meal", "Pick up delivery \n at your door and \n enjoy meal", "onboarding3.json")
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        viewPagerOnPageChangedCallback()
    }

    private fun initViews() {
        binding.viewPager.adapter = onboardingAdapter
        binding.indicator.setViewPager(binding.viewPager)
    }

    private fun viewPagerOnPageChangedCallback() {
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(position == onboardingAdapter.itemCount - 1) {
                    val animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.app_name_animation)

                    binding.buttonNext.animation = animation
                    binding.buttonNext.text = "Finish"
                    binding.buttonNext.setOnClickListener {
                        lifecycleScope.launch {
                            saveOnboarding()
                        }

                        requireView().findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToCityListFragment())
                    }
                } else {
                    binding.buttonNext.text = "Next"
                    binding.buttonNext.setOnClickListener {
                        binding.viewPager.currentItem.let { currentItem ->
                            binding.viewPager.setCurrentItem(currentItem + 1, false )
                        }
                    }
                }
            }
        })
    }

    suspend fun saveOnboarding() {
        preferences.edit {
            val oneTime = true
            it[preferencesKey<Boolean>("onboard")] = oneTime
        }
    }
}
package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.onboarding.OnboardingData
import com.iremcelikbilek.yemeksepetiapp.databinding.OnboardingItemLayoutBinding

class OnboardingAdapter(
    private val onboardingDatas: List<OnboardingData>
): RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    var onTextPassed: ((textView: TextView) -> Unit)? = null

    inner class OnboardingViewHolder(
        private val binding: OnboardingItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(onboardingItem: OnboardingData) {
            binding.textTitle.text = onboardingItem.title
            binding.textDescription.text = onboardingItem.description
            binding.imageSlideIcon.imageAssetsFolder = "assets"
            binding.imageSlideIcon.setAnimation(onboardingItem.icon)
            onTextPassed?.invoke(binding.textTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder = OnboardingViewHolder(
        OnboardingItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(onboardingDatas[position])
    }

    override fun getItemCount(): Int = onboardingDatas.size
}
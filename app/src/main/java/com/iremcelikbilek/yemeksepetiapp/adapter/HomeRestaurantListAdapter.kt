package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemHomeRestaurantListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.home.IRestaurantListItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import com.iremcelikbilek.yemeksepetiapp.utils.loadFromUrl

class HomeRestaurantListAdapter: RecyclerView.Adapter<HomeRestaurantListAdapter.HomeRestaurantListViewHolder>() {
    private var restaurantList: RestaurantListResponse? = null
    private var listener: IRestaurantListItemOnClick? = null

    fun setRestaurantList(restaurantList: RestaurantListResponse?) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    fun addListener(listener: IRestaurantListItemOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class HomeRestaurantListViewHolder(var binding: ItemHomeRestaurantListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: RestaurantData, listener: IRestaurantListItemOnClick?) {
            binding.restaurantImg.loadFromUrl(item.imageUrl)
            binding.restaurantNameTxt.text = item.name
            binding.minimumPriceTxt.text = item.minimumPrice
            binding.estimatedArrivalTimeTxt.text = item.estimatedArrivalTime
            binding.restaurantListItemLayout.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRestaurantListViewHolder {
        val view = ItemHomeRestaurantListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRestaurantListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRestaurantListViewHolder, position: Int) {
        ifNotNull(restaurantList?.data?.elementAtOrNull(position)) {
            holder.setItem(it, listener)
        }
    }

    override fun getItemCount(): Int = restaurantList?.data?.size ?: 0
}
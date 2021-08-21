package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iremcelikbilek.yemeksepetiapp.R
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.data.entity.search.SearchResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemHomeRestaurantListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.search.ISearchListOnClick

class SearchRestaurantListAdapter: RecyclerView.Adapter<SearchRestaurantListAdapter.SearchRestaurantListViewHolder>() {

    private var searchList: SearchResponse? = null
    private var listener: ISearchListOnClick? = null

    fun setSearchList(searchList: SearchResponse?) {
        this.searchList = searchList
        notifyDataSetChanged()
    }

    fun addListener(listener: ISearchListOnClick?) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class SearchRestaurantListViewHolder(var binding: ItemHomeRestaurantListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: RestaurantData, listener: ISearchListOnClick?) {
            Glide.with(binding.root.context).load(item.imageUrl).placeholder(R.drawable.loading).error(R.drawable.not_found).into(binding.restaurantImg)
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
    ): SearchRestaurantListViewHolder {
        val view = ItemHomeRestaurantListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRestaurantListViewHolder((view))
    }

    override fun onBindViewHolder(holder: SearchRestaurantListViewHolder, position: Int) {
        holder.setItem(searchList?.data!![position], listener)
    }

    override fun getItemCount(): Int = searchList?.data?.size ?: 0

}
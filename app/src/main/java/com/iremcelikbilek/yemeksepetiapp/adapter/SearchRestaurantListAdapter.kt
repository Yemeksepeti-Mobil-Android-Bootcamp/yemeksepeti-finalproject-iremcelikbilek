package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
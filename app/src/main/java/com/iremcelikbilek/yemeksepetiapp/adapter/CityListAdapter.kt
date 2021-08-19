package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityData
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemCityListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.citylist.ICityListItemOnClick

class CityListAdapter: RecyclerView.Adapter<CityListAdapter.CityListViewHolder>() {

    private var cityList: CityListResponse? = null
    private var listener: ICityListItemOnClick? = null

    fun setCityList(cityList: CityListResponse?) {
        this.cityList = cityList
        notifyDataSetChanged()
    }

    fun addListener(listener: ICityListItemOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class CityListViewHolder(var binding: ItemCityListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CityData, listener: ICityListItemOnClick?) {
            binding.cityIdTxt.text = item.id
            binding.cityNameTxt.text = item.name
            binding.cityListItemLayout.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val view = ItemCityListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.setItem(cityList?.data!![position], listener)
    }

    override fun getItemCount(): Int = cityList?.data?.size ?: 0
}
package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Menu
import com.iremcelikbilek.yemeksepetiapp.data.entity.restaurantList.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemMenuListBinding

class MenuListAdapter: RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {

    private var menuList: RestaurantData? = null

    fun setMenuList(menuList: RestaurantData?) {
        this.menuList = menuList
        notifyDataSetChanged()
    }

    class MenuListViewHolder(var binding: ItemMenuListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: Menu) {
            binding.menuNameTxt.text = item.name
            binding.menuDescriptionTxt.text = item.description
            binding.menuItemPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val view = ItemMenuListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.setItem(menuList?.menu!![position])
    }

    override fun getItemCount(): Int = menuList?.menu?.size ?: 0
}
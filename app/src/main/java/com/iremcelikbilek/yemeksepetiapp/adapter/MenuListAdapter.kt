package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Menu
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.RestaurantData
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemMenuListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.menuList.IMenuListOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import com.iremcelikbilek.yemeksepetiapp.utils.loadFromUrl

class MenuListAdapter: RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    private var menuList: RestaurantData? = null
    private var listener: IMenuListOnClick? = null

    fun setMenuList(menuList: RestaurantData?) {
        this.menuList = menuList
        notifyDataSetChanged()
    }

    fun addListener(listener: IMenuListOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class MenuListViewHolder(var binding: ItemMenuListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: Menu, listener: IMenuListOnClick?) {
            binding.menuImg.loadFromUrl(item.imageUrl)
            binding.menuNameTxt.text = item.name
            binding.menuDescriptionTxt.text = item.description
            binding.menuItemPrice.text = item.price
            binding.itemMenuListLayout.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val view = ItemMenuListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        ifNotNull(menuList?.menu?.elementAtOrNull(position)) {
            holder.setItem(it, listener)
        }
    }

    override fun getItemCount(): Int = menuList?.menu?.size ?: 0
}
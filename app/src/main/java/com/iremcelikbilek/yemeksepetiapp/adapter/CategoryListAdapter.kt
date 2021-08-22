package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryData
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemCategoryListBinding
import com.iremcelikbilek.yemeksepetiapp.ui.home.ICategoryItemOnClick
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import com.iremcelikbilek.yemeksepetiapp.utils.loadFromUrl

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {
    private var categoryList: CategoryResponse? = null
    private var listener: ICategoryItemOnClick? = null

    fun setCategoryList(categoryList: CategoryResponse?) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun addListener(listener: ICategoryItemOnClick) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    class CategoryListViewHolder(var binding: ItemCategoryListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CategoryData, listener: ICategoryItemOnClick?) {
            binding.categoryImg.loadFromUrl(item.imageUrl)
            binding.categoryNameTxt.text = item.name
            binding.categoryListItemLayout.setOnClickListener {
                listener?.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val view = ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        ifNotNull(categoryList?.data?.elementAtOrNull(position)) {
            holder.setItem(it, listener)
        }
    }

    override fun getItemCount(): Int = categoryList?.data?.size ?: 0
}
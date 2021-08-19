package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryData
import com.iremcelikbilek.yemeksepetiapp.data.entity.category.CategoryResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemCategoryListBinding

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    private var categoryList: CategoryResponse? = null

    fun setCategoryList(categoryList: CategoryResponse) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    class CategoryListViewHolder(var binding: ItemCategoryListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CategoryData) {
            binding.categoryNameTxt.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val view = ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.setItem(categoryList?.data!![position])
    }

    override fun getItemCount(): Int = categoryList?.data?.size ?: 0
}
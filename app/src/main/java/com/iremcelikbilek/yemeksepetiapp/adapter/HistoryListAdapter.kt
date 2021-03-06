package com.iremcelikbilek.yemeksepetiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.data.entity.cart.CartData
import com.iremcelikbilek.yemeksepetiapp.data.entity.history.HistoryResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.ItemHistoryListBinding
import com.iremcelikbilek.yemeksepetiapp.utils.ifNotNull
import com.iremcelikbilek.yemeksepetiapp.utils.loadFromUrl

class HistoryListAdapter: RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder>() {
    private var historyList: HistoryResponse? = null

    fun setHistoryList(historyList: HistoryResponse?) {
        this.historyList = historyList
        notifyDataSetChanged()
    }

    class HistoryListViewHolder(var binding: ItemHistoryListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CartData) {
            binding.menuImg.loadFromUrl(item.menu.imageUrl)
            binding.restaurantNameTxt.text = item.name
            binding.menuNameTxt.text = item.menu.name
            binding.menuDescriptionTxt.text = item.menu.description
            binding.priceTxt.text = item.menu.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val view = ItemHistoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  HistoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        ifNotNull(historyList?.data?.elementAtOrNull(position)) {
            holder.setItem(it)
        }
    }

    override fun getItemCount(): Int = historyList?.data?.size ?: 0
}
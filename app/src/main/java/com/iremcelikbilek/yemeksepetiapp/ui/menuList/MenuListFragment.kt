package com.iremcelikbilek.yemeksepetiapp.ui.menuList

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iremcelikbilek.yemeksepetiapp.adapter.MenuListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.common.Menu
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentMenuListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuListFragment : Fragment() {

    private lateinit var binding: FragmentMenuListBinding
    private val menuListAdapter: MenuListAdapter = MenuListAdapter()
    private val args by navArgs<MenuListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuListRv.layoutManager = LinearLayoutManager(context)
        binding.menuListRv.adapter = menuListAdapter
        menuListAdapter.setMenuList(args.restaurantData)
        binding.restaurantNameTxt.text = args.restaurantData.name
        binding.minimumPriceTxt.text = args.restaurantData.minimumPrice
        binding.estimatedArrivalTimeTxt.text = args.restaurantData.estimatedArrivalTime

        menuListAdapter.addListener(object: IMenuListOnClick{
            override fun onClick(item: Menu) {
                val restaurantName = args.restaurantData.name
                findNavController().navigate(MenuListFragmentDirections.actionMenuListFragmentToMenuDetailFragment(item,restaurantName))
            }

        })

    }
}

/*binding.collapsingToolbar.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            if(bottom != oldBottom) binding.toolbarTxt.text = "Menu Listesi"
        }


        binding.menuListRv.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if(position > 0) {
                    binding.toolbarTxt.text = "Menü Listesi"
                }
            }
        })*/
package com.iremcelikbilek.yemeksepetiapp.ui.citylist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.iremcelikbilek.yemeksepetiapp.HomeActivity
import com.iremcelikbilek.yemeksepetiapp.adapter.CityListAdapter
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityData
import com.iremcelikbilek.yemeksepetiapp.data.entity.citylist.CityListResponse
import com.iremcelikbilek.yemeksepetiapp.databinding.FragmentCityListBinding
import com.iremcelikbilek.yemeksepetiapp.utils.Resource
import com.iremcelikbilek.yemeksepetiapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CityListFragment: Fragment() {
    private lateinit var binding: FragmentCityListBinding
    private val viewModel: CityListViewModel by viewModels()
    @Inject lateinit var preferences: DataStore<Preferences>
    private var cityListAdapter: CityListAdapter = CityListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeCityList()
    }

    private fun initViews() {
        binding.cityListRv.layoutManager = LinearLayoutManager(context)
        binding.cityListRv.adapter = cityListAdapter
    }

    private fun observeCityList() {
        viewModel.getCityList().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    setData(it.data)
                }

                Resource.Status.ERROR -> {
                    showAlert(it.message)
                }
            }
        })

    }

    private fun setData(data: CityListResponse?) {
        cityListAdapter.setCityList(data)

        cityListAdapter.addListener(object: ICityListItemOnClick {
            override fun onClick(item: CityData) {
                lifecycleScope.launch {
                    saveOneTime()
                }
                viewModel.saveCity(item.id)
                startActivity(Intent(context, HomeActivity::class.java))
                requireActivity().finish()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        cityListAdapter.removeListener()
    }

    suspend fun saveOneTime() {
        preferences.edit {
            val oneTime = true
            it[preferencesKey<Boolean>("oneTime")] = oneTime
        }
    }
}
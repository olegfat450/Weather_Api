package com.example.weather_api

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewAdapter(fragment: FragmentActivity, val list: List<Data>): FragmentStateAdapter(fragment){

    override fun getItemCount() = list.size


    override fun createFragment(position: Int): Fragment {
        val fragment = StartFragment()
            fragment.arguments = bundleOf("key1" to list[position],"key2" to position )

        return fragment
    }


}
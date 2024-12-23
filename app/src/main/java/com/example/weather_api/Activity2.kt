package com.example.weather_api

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

class Activity2 : FragmentActivity() {

     private lateinit var viewpager: ViewPager2
     private lateinit var adapter: ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        viewpager = findViewById(R.id.viewPagerTv)

        val list = Data.list


        adapter = ViewAdapter(this,list)

        viewpager.adapter = adapter

    }
}
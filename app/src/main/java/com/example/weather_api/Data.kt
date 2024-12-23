package com.example.weather_api

import java.io.Serializable

class Data (val text: String,val image: Int): Serializable {

    companion object {

        val d1 = Data("Рады Вас приветствовать в нашем мобильном приложении", R.drawable.w1)
        val d2 = Data("Оно поможет Вам узнать погоду в разных городах", R.drawable.w2)
        val d3 = Data("Программа предназначена исключительно для частного пользования", R.drawable.w3)

        val list = listOf(d1, d2, d3)
    }

}
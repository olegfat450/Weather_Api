package com.example.weather_api

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView


class StartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           val text = view.findViewById<TextView>(R.id.textTv)
           val image = view.findViewById<ImageView>(R.id.imageTv)
           val button = view.findViewById<RadioButton>(R.id.radioButton)

           val listItem = arguments?.getSerializable("key1") as Data

           val position = arguments?.getSerializable("key2")

           text.text = listItem.text
           image.setImageResource(listItem.image)

           if ( position == Data.list.size - 1) button.visibility = View.VISIBLE

           button.setOnClickListener {

               val intent = Intent(this@StartFragment.context,MainActivity::class.java)
                    intent.putExtra("key",111)
                    startActivity(intent)

           }


    }

}


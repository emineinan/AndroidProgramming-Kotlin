package com.example.countriesbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countries=ArrayList<String>()
        countries.add("Turkey")
        countries.add("England")
        countries.add("Japan")
        countries.add("Spain")

        val turkeyDrawableId=R.drawable.turkey
        val englandDrawableId=R.drawable.england
        val japanDrawableId=R.drawable.japan
        val spainDrawableId=R.drawable.spain

        var countriesDrawableList=ArrayList<Int>()
        countriesDrawableList.add(turkeyDrawableId)
        countriesDrawableList.add(englandDrawableId)
        countriesDrawableList.add(japanDrawableId)
        countriesDrawableList.add(spainDrawableId)

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val adapter=RecyclerAdapter(countries,countriesDrawableList)
        recyclerView.adapter=adapter
    }
}
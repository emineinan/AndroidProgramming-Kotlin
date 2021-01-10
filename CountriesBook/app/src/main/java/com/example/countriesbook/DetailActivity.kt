package com.example.countriesbook

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent=intent
        val selectedCountryName=intent.getStringExtra("countryName")
        textView.text=selectedCountryName

        val selectedImage=intent.getIntExtra("countryImage",0)
        val bitmap=BitmapFactory.decodeResource(applicationContext.resources,selectedImage)
        imageView.setImageBitmap(bitmap)
    }
}
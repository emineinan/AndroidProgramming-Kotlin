package com.example.differentactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goSecondActivity(view: View){
        val userData=editText.text.toString()

        val intent=Intent(applicationContext,MainActivity2::class.java)
        intent.putExtra("sentData",userData)
        startActivity(intent)
    }
}
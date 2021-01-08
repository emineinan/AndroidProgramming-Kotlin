package com.example.differentactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent=intent
        val receivedData=intent.getStringExtra("sentData")
        textView2.text=receivedData
        Toast.makeText(this,"Welcome!",Toast.LENGTH_LONG).show()
    }
}
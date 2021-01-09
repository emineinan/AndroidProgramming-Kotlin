package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var counter=0
    var runnable:Runnable=Runnable{}
    var handler= Handler(Looper.myLooper()!!)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View){
        counter=0
        runnable= object : Runnable {
            override fun run() {
                counter++
                textView.text="Counter: ${counter}"
                handler.postDelayed(runnable,1000)
            }

        }
             handler.post(runnable)
    }

    fun reset(view: View){
        handler.removeCallbacks(runnable)
        counter=0
        textView.text="Counter: 0"


    }
}
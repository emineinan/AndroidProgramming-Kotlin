package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences:SharedPreferences
    var gettingUsername:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences=this.getSharedPreferences("com.example.sharedpreferences",
        Context.MODE_PRIVATE)

        gettingUsername=sharedPreferences.getString("user","")
        if(gettingUsername!=null){
            textView.text="Saved username: ${gettingUsername}"
        }
    }

    fun save(view: View){
        val username=editText.text.toString()
        if(username==""){
            Toast.makeText(this,"Please enter an username",Toast.LENGTH_LONG).show()
        }else{
            sharedPreferences.edit().putString("user",username).apply()
            textView.text="Saved username:${username}"
        }
    }
    fun delete(view: View){
        gettingUsername=sharedPreferences.getString("user","")
        if(gettingUsername!=null){
            textView.text="Saved username:"
            sharedPreferences.edit().remove("user").apply()
        }
    }
}
package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var num1:Int?=null
    var num2:Int?=null
    var result:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun add(view: View){
        num1=editText.text.toString().toIntOrNull()
        num2=editText2.text.toString().toIntOrNull()

        if(num1==null||num2==null){
            textView.text="Please enter a number!"
        }else{
            result=num1!!+num2!!;
            textView.text="RESULT: ${result}"
        }
    }

    fun extract(view: View){
        num1=editText.text.toString().toIntOrNull()
        num2=editText2.text.toString().toIntOrNull()

        if(num1==null||num2==null){
            textView.text="Please enter a number!"
        }else{
            result=num1!!-num2!!;
            textView.text="RESULT: ${result}"
        }

    }

    fun multiply(view: View){
        num1=editText.text.toString().toIntOrNull()
        num2=editText2.text.toString().toIntOrNull()

        if(num1==null||num2==null){
            textView.text="Please enter a number!"
        }else{
            result=num1!!*num2!!;
            textView.text="RESULT: ${result}"
        }

    }

    fun divide(view: View){
        num1=editText.text.toString().toIntOrNull()
        num2=editText2.text.toString().toIntOrNull()

        if(num1==null||num2==null){
            textView.text="Please enter a number!"
        }else{
            result=num1!!/num2!!;
            textView.text="RESULT: ${result}"
        }

    }
}
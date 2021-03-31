package com.example.intentsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val myText = intent.getStringExtra("text")
        textViewDisplay.text = myText
    }

    fun goToMainPage(view: View) {
        val i = Intent()
        i.putExtra("returnText", "HELLO MAIN PAGE")
        setResult(Activity.RESULT_OK, i)
        finish()
    }
}
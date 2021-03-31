package com.example.intentsample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToSecondPage(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("text", "HELLO SECOND PAGE")
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val str = data?.getStringExtra("returnText")
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        }
    }
}
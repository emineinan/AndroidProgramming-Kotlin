package com.example.fragmentsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.bigContainer, BigFragment())
        fragmentTransaction.commit()

        smallContainer.visibility = View.INVISIBLE
    }

    fun makeSmaller(view: View) {
        smallContainer.visibility = View.VISIBLE

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.smallContainer, SmallFragment())
        fragmentTransaction.commit()
    }
}
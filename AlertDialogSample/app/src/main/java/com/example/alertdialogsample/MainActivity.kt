package com.example.alertdialogsample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showAlertDialog(){
        var alertBuilder=AlertDialog.Builder(this)
        alertBuilder.setTitle("WARNING")
        alertBuilder.setMessage("Do you want to exit the application?")

        alertBuilder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
            finish()
        })

        alertBuilder.setNegativeButton("NO",null)

        var showAlert=alertBuilder.create()
        showAlert.show()
    }

    override fun onBackPressed() {
        showAlertDialog()
    }
}
package com.example.sqlitesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db=DBHelper(this)
        PersonDao().insertPerson(db,"Ali Yılmaz","5353008090")
        PersonDao().updatePerson(db,1,"Ali Şahin","5409087060")
        PersonDao().insertPerson(db,"Ayse Yılmaz","5353008095")
        PersonDao().deletePerson(db,1)

        val personList=PersonDao().getAllPerson(db)
        for(p in personList){
            Log.e("Person id",(p.person_id).toString())
            Log.e("Person name",p.person_name)
            Log.e("Person phone",p.person_phone)
        }
    }
}
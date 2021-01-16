package com.example.traveldiary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class ListFragment : Fragment() {

    var cityNameList=ArrayList<String>()
    var cityIdList=ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sqlGetData()
        super.onViewCreated(view, savedInstanceState)
    }

    fun sqlGetData(){
        try {
            activity?.let {
                val database=it.openOrCreateDatabase("Travels", Context.MODE_PRIVATE,null)
                val cursor=database.rawQuery("SELECT * FROM travels",null)
                val cityNameIndex=cursor.getColumnIndex("cityName")
                val cityIdIndex=cursor.getColumnIndex("id")

                cityIdList.clear()
                cityNameList.clear()

                while (cursor.moveToNext()){
                    cityNameList.add(cursor.getString(cityNameIndex))
                    cityIdList.add(cursor.getInt(cityIdIndex))
                }
                cursor.close()
            }

        }catch (e:Exception){

        }
    }
}
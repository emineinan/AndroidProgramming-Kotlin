package com.example.traveldiary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    var cityNameList=ArrayList<String>()
    var cityIdList=ArrayList<Int>()
    private lateinit var listAdaptr:ListRecyclerAdapter

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
        super.onViewCreated(view, savedInstanceState)
        listAdaptr= ListRecyclerAdapter(cityNameList,cityIdList)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=listAdaptr
        sqlGetData()
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
                listAdaptr.notifyDataSetChanged()
                cursor.close()
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
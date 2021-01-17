package com.example.traveldiary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class ListRecyclerAdapter(val travelList:ArrayList<String>,val idList:ArrayList<Int>):RecyclerView.Adapter<ListRecyclerAdapter.TravelHolder>() {
    class TravelHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return TravelHolder(view)
    }

    override fun getItemCount(): Int {
        return idList.size
    }

    override fun onBindViewHolder(holder: TravelHolder, position: Int) {
        holder.itemView.recycler_text.text=travelList[position]
    }
}
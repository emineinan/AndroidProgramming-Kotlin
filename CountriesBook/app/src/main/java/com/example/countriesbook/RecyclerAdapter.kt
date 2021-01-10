package com.example.countriesbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val countriesList: ArrayList<String>,val countriesImages:ArrayList<Int>):RecyclerView.Adapter<RecyclerAdapter.CountriesVH>() {
    class CountriesVH(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return CountriesVH(itemView)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountriesVH, position: Int) {
        holder.itemView.recyclerViewText.text=countriesList.get(position)
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("countryName",countriesList.get(position))
            intent.putExtra("countryImage",countriesImages.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }
}
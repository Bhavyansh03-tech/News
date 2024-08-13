package com.example.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.models.SetUpModel

class ThirdAdapter(private val context: Context, private val itemList: ArrayList<SetUpModel>): RecyclerView.Adapter<ThirdAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ryc, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = itemList[position]
        holder.hTxt.text = current.title

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(holder.recycler)

        val adapter = LinearAdapter(context, current.list)
        holder.recycler.layoutManager = LinearLayoutManager(context)
        holder.recycler.setHasFixedSize(true)
        holder.recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recycler: RecyclerView = itemView.findViewById(R.id.linear_recyclerView)
        val hTxt: TextView = itemView.findViewById(R.id.heading_text)
    }
}
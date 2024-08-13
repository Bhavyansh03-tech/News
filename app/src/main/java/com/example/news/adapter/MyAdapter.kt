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
import com.example.news.models.BreakingModel

class MyAdapter(private val context: Context, private val itemList: ArrayList<BreakingModel>) :
    RecyclerView.Adapter<MyAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.text1.text = currentItem.headingText
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(holder.recycler)

        val adapter = HorizontalAdapter(context, currentItem.list)
        holder.recycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        holder.recycler.setHasFixedSize(true)
        holder.recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycler: RecyclerView = itemView.findViewById(R.id.recyclerView)
        val text1: TextView = itemView.findViewById(R.id.heading_text)
    }
}
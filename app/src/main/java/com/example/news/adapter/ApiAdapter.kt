package com.example.news.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.apiModels.Article

class ApiAdapter(private val context: Context, private val list: ArrayList<Article>) :
    RecyclerView.Adapter<ApiAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.breaking_style, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.text1.text = current.title
        holder.itemView.setOnClickListener {
            val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
            context.startActivity(website)
        }
        Glide.with(context).load(current.urlToImage).into(holder.image1)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image1: ImageView = itemView.findViewById(R.id.image)
        val text1: TextView = itemView.findViewById(R.id.news_txt)
    }
}
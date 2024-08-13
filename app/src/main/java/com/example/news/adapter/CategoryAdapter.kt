package com.example.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.apiModels.Article
import com.example.news.onClick.OnClickActivity

class CategoryAdapter(private val context: Context, private val list: ArrayList<Article>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]
        holder.title.text = current.title
        holder.date.text = current.publishedAt
        holder.author.text = current.author
        holder.itemView.setOnClickListener {
            val intent = Intent(context, OnClickActivity::class.java)
            intent.putExtra("url", current.url)
            intent.putExtra("title", current.title)
            intent.putExtra("author", current.author)
            intent.putExtra("description", current.description)
            intent.putExtra("publishedAt", current.publishedAt)
            intent.putExtra("urlToImage", current.urlToImage)
            context.startActivity(intent)
        }
        Glide.with(context).load(current.urlToImage).into(holder.img)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img : ImageView = itemView.findViewById(R.id.category_img)
        val title: TextView = itemView.findViewById(R.id.category_txt)
        val author: TextView = itemView.findViewById(R.id.category_author)
        val date: TextView = itemView.findViewById(R.id.category_date)
    }
}
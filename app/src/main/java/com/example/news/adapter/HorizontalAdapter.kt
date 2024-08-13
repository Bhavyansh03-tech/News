package com.example.news.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.BreakingStyleBinding
import com.example.news.databinding.PhotoLayoutBinding
import com.example.news.databinding.RecentStyleBinding
import com.example.news.models.RecentModel
import com.example.news.utils.Constants

class HorizontalAdapter(private val context: Context, private val itemList: ArrayList<RecentModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.BREAKING -> {
                val binding = BreakingStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BreakingNews(binding)
            }
            Constants.RECENT -> {
                val binding = RecentStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecentNews(binding)
            }
            else -> {
                val binding = PhotoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PhotoNews(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].postType) {
            0 -> {
                Constants.BREAKING
            }
            1 -> {
                Constants.RECENT
            }
            else -> {
                Constants.PIC
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = itemList[position]
        if (getItemViewType(position) == Constants.BREAKING){
            (holder as BreakingNews).breaking(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }else if (getItemViewType(position) == Constants.RECENT){
            (holder as RecentNews).recent(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }else{
            (holder as PhotoNews).photo(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }
    }

    class BreakingNews(private val binding: BreakingStyleBinding): RecyclerView.ViewHolder(binding.root){
        fun breaking(article: RecentModel){
            Glide.with(binding.root.context).load(article.image).into(binding.image)
            binding.newsTxt.text = article.heading
        }
    }
    class RecentNews(private val binding: RecentStyleBinding): RecyclerView.ViewHolder(binding.root){
        fun recent(article: RecentModel){
            Glide.with(binding.root.context).load(article.image).into(binding.image2)
            binding.text2.text = article.heading
            binding.textDate.text = article.date
        }
    }
    class PhotoNews(private val binding: PhotoLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun photo(article: RecentModel){
            Glide.with(binding.root.context).load(article.image).into(binding.imageImg)
            binding.imgTxt.text = article.heading
            binding.imgDate.text = article.date
        }
    }
}
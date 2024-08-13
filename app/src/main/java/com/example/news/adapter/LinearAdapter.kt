package com.example.news.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.CardLayoutBinding
import com.example.news.databinding.LayoutBinding
import com.example.news.databinding.PhotoLayoutBinding
import com.example.news.models.PhotoModel
import com.example.news.utils.Constants

class LinearAdapter(private val context: Context, private val itemList: ArrayList<PhotoModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.PHOTO -> {
                val binding = PhotoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PhotoNews(binding)
            }
            Constants.CARD -> {
                val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CardNews(binding)
            }
            else -> {
                val binding = LayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LinearNews(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].post) {
            3 -> {
                Constants.PHOTO
            }
            4 -> {
                Constants.CARD
            }
            else -> {
                Constants.LINEAR
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = itemList[position]
        if (getItemViewType(position) == Constants.PHOTO){
            (holder as PhotoNews).photo(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }else if (getItemViewType(position) == Constants.CARD){
            (holder as CardNews).card(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }else{
            (holder as LinearNews).linear(itemList[position])
            holder.itemView.setOnClickListener {
                val website = Intent(Intent.ACTION_VIEW, Uri.parse(current.url))
                context.startActivity(website)
            }
        }
    }

    class PhotoNews(private val binding: PhotoLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun photo(article: PhotoModel){
            Glide.with(binding.root.context).load(article.image).into(binding.imageImg)
            binding.imgDate.text = article.date
            binding.imgTxt.text = article.heading
        }
    }
    class CardNews(private val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun card(article: PhotoModel){
            Glide.with(binding.root.context).load(article.image1).into(binding.i1)
            Glide.with(binding.root.context).load(article.image2).into(binding.i2)
            Glide.with(binding.root.context).load(article.image3).into(binding.i3)
            Glide.with(binding.root.context).load(article.image4).into(binding.i4)
            binding.h1.text = article.heading1
            binding.h2.text = article.heading2
            binding.h3.text = article.heading3
            binding.h4.text = article.heading4
        }
    }
    class LinearNews(private val binding: LayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun linear(article: PhotoModel){
            Glide.with(binding.root.context).load(article.image).into(binding.image2)
            binding.titleTxt.text = article.heading
            binding.txtDate.text = article.date
            binding.imgLayout.setOnClickListener {
                
            }
        }
    }

}
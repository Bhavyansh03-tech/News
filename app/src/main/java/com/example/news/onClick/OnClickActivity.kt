package com.example.news.onClick

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.example.news.databinding.ActivityOnClickBinding

class OnClickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnClickBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnClickBinding.inflate(layoutInflater)
        supportRequestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)

        // Status Bar inside text color :->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Getting Values :->
        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val description = intent.getStringExtra("description")
        val publishedAt = intent.getStringExtra("publishedAt")
        val urlToImage = intent.getStringExtra("urlToImage")

        // Inserting values :->
        binding.onClickTitle.text = title
        binding.onClickDate.text = publishedAt
        binding.authorName.text = author
        binding.description.text = description
        Glide.with(this).load(urlToImage).into(binding.onClickImageView)

        // OnClick URL :->
        binding.urlToNewsBtn.setOnClickListener {
            val website = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(website)
        }

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//    }
}
package com.example.news.apiModels

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
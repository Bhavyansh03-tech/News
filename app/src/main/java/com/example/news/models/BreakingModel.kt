package com.example.news.models


data class BreakingModel(
    var dataType: Int?,
    var headingText: String?,
    var list: ArrayList<RecentModel>
)

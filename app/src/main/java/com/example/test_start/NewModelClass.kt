package com.example.test_start

data class News(
    val news: ArrayList<NewModelClass>
)

data class NewModelClass(
    val date: String,
    val description: String,
    val id: Int,
    val keywords: List<String>,
    val title: String,
)
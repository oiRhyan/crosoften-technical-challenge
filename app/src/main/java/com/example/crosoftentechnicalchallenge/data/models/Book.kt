package com.example.crosoftentechnicalchallenge.data.models

data class Book(
    val author: String,
    val category: Category,
    val createdAt: String?,
    val id: Int,
    val imageUrl: String,
    val summary: String?,
    val title: String,
    val updatedAt: String?
)
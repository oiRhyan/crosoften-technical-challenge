package com.example.crosoftentechnicalchallenge.data.models

data class Item(
    val data: List<Book>,
    val itemsPerPage: Int,
    val page: Int,
    val totalItems: Int,
    val totalPages: Int
)
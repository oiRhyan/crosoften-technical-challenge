package com.example.crosoftentechnicalchallenge.presentation.ui.state

import com.example.crosoftentechnicalchallenge.data.models.Book

sealed class SearchUIState{
    data object isLoading : SearchUIState()
    data object Empty : SearchUIState()
    data class Success(val books: List<Book> = emptyList() ) : SearchUIState()
    data class Error(val message: String) : SearchUIState()
}

data class SearchScreenUIState(
    val search: String = "",
    val onSearch: (String) -> Unit = {}
)
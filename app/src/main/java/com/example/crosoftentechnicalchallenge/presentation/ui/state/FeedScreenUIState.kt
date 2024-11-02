package com.example.crosoftentechnicalchallenge.presentation.ui.state

import com.example.crosoftentechnicalchallenge.data.models.Book

sealed class FeedScreenUIState {
    data object isLoading : FeedScreenUIState()
    data class Success(val books: List<Book> = emptyList() ) : FeedScreenUIState()
    data class Error(val message: String) : FeedScreenUIState()
}
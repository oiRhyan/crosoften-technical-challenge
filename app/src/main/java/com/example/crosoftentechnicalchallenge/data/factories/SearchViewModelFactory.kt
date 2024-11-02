package com.example.crosoftentechnicalchallenge.data.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crosoftentechnicalchallenge.presentation.ui.views.details.SearchViewModel

class SearchViewModelFactory(private val app : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
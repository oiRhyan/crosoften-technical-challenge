package com.example.crosoftentechnicalchallenge.data.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crosoftentechnicalchallenge.presentation.ui.views.feed.FeedViewModel


class FeedViewModelFactory(private val app : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
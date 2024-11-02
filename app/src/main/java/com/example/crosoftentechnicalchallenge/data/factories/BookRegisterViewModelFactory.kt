package com.example.crosoftentechnicalchallenge.data.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crosoftentechnicalchallenge.presentation.ui.views.bookregister.BookRegisterViewModel

class BookRegisterViewModelFactory(private val app : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookRegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookRegisterViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
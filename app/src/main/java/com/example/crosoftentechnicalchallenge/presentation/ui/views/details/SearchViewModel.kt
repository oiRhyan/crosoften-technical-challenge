package com.example.crosoftentechnicalchallenge.presentation.ui.views.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crosoftentechnicalchallenge.data.repository.BooksRepository
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.presentation.ui.state.SearchScreenUIState
import com.example.crosoftentechnicalchallenge.presentation.ui.state.SearchUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BooksRepository(RetrofitService.getServiceInstace())

    private val _query = MutableStateFlow(SearchScreenUIState())
    val query: StateFlow<SearchScreenUIState> = _query.asStateFlow()

    private val _search = MutableStateFlow<SearchUIState>(SearchUIState.Empty)
    val search: StateFlow<SearchUIState> = _search.asStateFlow()

    init {
        updateSearchQuery()
    }

    private fun updateSearchQuery() {
        _query.update { state ->
            state.copy(
                onSearch = {
                    _query.value = _query.value.copy(search = it)
                }
            )
        }
    }

    fun searchBook() {
        val token = PreferencesHelper.getToken(getApplication())
        val bearer = "Bearer $token"
        viewModelScope.launch {
            val response = repository.searchBooks(bearer, _query.value.search)
            _search.value = SearchUIState.isLoading
            if (response.isSuccessful) {
                _search.value = SearchUIState.Success(response.body()?.data ?: emptyList())
                Log.v("Success", "Books fetched: ${response.body()}")
            } else {
                _search.value = SearchUIState.Error(response.errorBody()?.string() ?: "Unknown error")
                Log.v("Error", response.errorBody()?.string() ?: "Unknown error")
            }
        }
    }
}
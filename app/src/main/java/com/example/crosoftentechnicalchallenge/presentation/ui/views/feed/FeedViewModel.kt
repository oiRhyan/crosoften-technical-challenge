package com.example.crosoftentechnicalchallenge.presentation.ui.views.feed

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.crosoftentechnicalchallenge.data.repository.BooksRepository
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.presentation.ui.state.FeedScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val app : Application
) : AndroidViewModel(app) {

    private var _state = MutableStateFlow<FeedScreenUIState>(FeedScreenUIState.isLoading)
    val state = _state.asStateFlow()

    private val repository = BooksRepository(
        RetrofitService.getServiceInstace()
    )

    init {
        loadBooks()
    }

    fun deleteBook(id: Int, app: Context) {
        val token = PreferencesHelper.getToken(context = app)
        val bearer = "Bearer $token"
        viewModelScope.launch {
            val response = repository.deleteBook(bearer, id)
            if (response.isSuccessful) {
                Log.v("Deletar Livro", "Livro deletado com sucesso")
                loadBooks()
            } else {
                Log.v("Deletar Livro", "Erro ao deletar livro")
            }
        }
    }

    fun loadBooks() {
        val token = PreferencesHelper.getToken(app)
        Log.v("token" , token.toString())
        if (token != null) {
            getAllBooks(token)
        } else {
            _state.value = FeedScreenUIState.Error("Token não encontrado. Faça login novamente.")
        }
    }

    private fun getAllBooks(token: String) {
        val bearerToken = "Bearer $token"
        viewModelScope.launch {
            try {
                _state.value = FeedScreenUIState.isLoading
                val response = repository.getAllBooks(bearerToken)
                if (response.isSuccessful) {
                    _state.value = FeedScreenUIState.Success(response.body()?.data ?: emptyList())
                    response.body()?.toString()?.let { Log.v("books" , it) }
                } else {
                    _state.value = FeedScreenUIState.Error("Erro: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                _state.value = FeedScreenUIState.Error("Erro ao carregar livros")
            }
        }
    }

}
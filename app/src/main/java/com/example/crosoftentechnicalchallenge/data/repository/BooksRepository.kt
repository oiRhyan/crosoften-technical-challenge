package com.example.crosoftentechnicalchallenge.data.repository

import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.presentation.ui.state.BookRegister
import okhttp3.MultipartBody

class BooksRepository(private val service : RetrofitService) {

    suspend fun getAllBooks(token: String)  = service.getAllBooks(token)
    suspend fun uploadBook(token: String, book: BookRegister) = service.uploadBook(token, book)
    suspend fun uploadBookImage(token:String, file: MultipartBody.Part ) = service.uploadFile(token,file)
    suspend fun deleteBook(token: String, id: Int) = service.deleteBook(token, id)
    suspend fun searchBooks(token: String, query: String) = service.searchBook(token, query)

}
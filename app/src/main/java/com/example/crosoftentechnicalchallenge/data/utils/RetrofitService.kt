package com.example.crosoftentechnicalchallenge.data.utils

import com.example.crosoftentechnicalchallenge.data.models.Book
import com.example.crosoftentechnicalchallenge.data.models.Item
import com.example.crosoftentechnicalchallenge.data.models.Credential
import com.example.crosoftentechnicalchallenge.data.models.Token
import com.example.crosoftentechnicalchallenge.data.models.UploadResponse
import com.example.crosoftentechnicalchallenge.data.models.User
import com.example.crosoftentechnicalchallenge.presentation.ui.state.BookRegister
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @POST("users/")
    suspend fun registerUser(@Body user: User) : Response<User>

    @POST("auth/login")
    suspend fun loginUser(@Body credential: Credential) : Response<Token>

    @GET("/books?limit=20")
    suspend fun getAllBooks(
        @Header("Authorization") token: String,
    ) : Response<Item>

    @Multipart
    @POST("/upload-file")
    suspend fun uploadFile(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
    ): Response<UploadResponse>

    @DELETE("/books/{id}")
    suspend fun deleteBook(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @POST("/books")
    suspend fun uploadBook(
        @Header("Authorization") token: String,
        @Body book: BookRegister
    ) : Response<Book>


    @GET("/books")
    suspend fun searchBook(
        @Header("Authorization") token: String,
        @Query("search") searchQuery: String
    ): Response<Item>

    companion object {
        private val retrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://45.10.163.203:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getServiceInstace() : RetrofitService {
            return retrofitService
        }
    }
}
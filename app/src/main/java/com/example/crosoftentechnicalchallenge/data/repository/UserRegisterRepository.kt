package com.example.crosoftentechnicalchallenge.data.repository

import com.example.crosoftentechnicalchallenge.data.models.Credential
import com.example.crosoftentechnicalchallenge.data.models.User
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService

class UserRegisterRepository(private val service : RetrofitService) {

    suspend fun registerUser(user : User)  = service.registerUser(user)
    suspend fun loginUser(credential : Credential) = service.loginUser(credential)

}
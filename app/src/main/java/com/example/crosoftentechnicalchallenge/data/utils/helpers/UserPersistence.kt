package com.example.crosoftentechnicalchallenge.data.utils.helpers

import com.example.crosoftentechnicalchallenge.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


object UserPersistence {

    private var _user = MutableStateFlow<User>(User(name = "", email = "", password = "", confirmPassword = ""))
    private val user = _user.asStateFlow()

    fun setUser(user : User) {
        _user.value = user
    }

    fun getUser() : StateFlow<User> {
        return user
    }
}
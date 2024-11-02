package com.example.crosoftentechnicalchallenge.presentation.ui.state

import android.util.Log
import com.example.crosoftentechnicalchallenge.data.models.User

data class RegisterUserState(
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val confirmPassword: String = "",
    val onChangedUserName: (String) -> Unit = {},
    val onChangedUserPassword: (String) -> Unit = {},
    val onChangedConfirmPassword: (String) -> Unit = {},
    val onChangedUserEmail: (String) -> Unit = {},
    val onRegisterUser: () -> Unit = {}
) {
    val passwordConfirm : Boolean
        get() = userPassword == confirmPassword
}

fun RegisterUserState.toUser(): User {
    return User(
        name = userName,
        email = userEmail,
        password = userPassword,
        confirmPassword = confirmPassword
    )
}

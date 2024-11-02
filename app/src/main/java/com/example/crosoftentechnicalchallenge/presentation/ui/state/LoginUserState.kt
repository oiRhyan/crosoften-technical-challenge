package com.example.crosoftentechnicalchallenge.presentation.ui.state

import com.example.crosoftentechnicalchallenge.data.models.Credential
import com.example.crosoftentechnicalchallenge.data.models.User


data class LoginUserState (
    val userEmail : String = "",
    val userPassword : String = "",
    val onChangedUserEmail : (String) -> Unit = {},
    val onChangedUserPassword : (String) -> Unit = {},
)

fun LoginUserState.toCredential() : Credential {
    return Credential(
        credential = userEmail,
        password = userPassword
    )
}

fun LoginUserState.toUser() : User {
    return User(
        name = "",
        email = userEmail,
        password = "",
        confirmPassword = ""
    )
}
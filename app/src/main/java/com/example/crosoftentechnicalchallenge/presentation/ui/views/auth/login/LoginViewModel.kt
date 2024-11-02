package com.example.crosoftentechnicalchallenge.presentation.ui.views.auth.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crosoftentechnicalchallenge.data.repository.UserRegisterRepository
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.data.utils.helpers.UserPersistence
import com.example.crosoftentechnicalchallenge.presentation.ui.state.LoginUserState
import com.example.crosoftentechnicalchallenge.presentation.ui.state.toCredential
import com.example.crosoftentechnicalchallenge.presentation.ui.state.toUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val app : Application
) : AndroidViewModel(app) {

    private val repository = UserRegisterRepository(
        RetrofitService.getServiceInstace()
    )

    private val _state = MutableStateFlow<Boolean>(false)
    val state = _state.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    private val _loginState = MutableStateFlow<LoginUserState>(LoginUserState())
    val loginState = _loginState.asStateFlow()

    init {
        updateLoginState()
    }

    private fun updateLoginState() {
        _loginState.update { state ->
            state.copy(
                onChangedUserEmail = {
                    _loginState.value = _loginState.value.copy(userEmail = it)
                },
                onChangedUserPassword = {
                    _loginState.value = _loginState.value.copy(userPassword = it)
                }
            )
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            val credential = _loginState.value.toCredential()
            val response = repository.loginUser(credential)

            UserPersistence.setUser(_loginState.value.toUser())

            if(response.isSuccessful) {
                val body = response.body()
                _token.value = body?.token
                body?.token?.let { token ->
                    PreferencesHelper.saveToken(app, token)
                }
                Log.d("Login", body.toString())
                _state.value = false
            } else {
                _state.value = true
                Log.d("Login", "Erro: ${response.code()} - ${response.message()}")
            }
        }
    }

}
package com.example.crosoftentechnicalchallenge.presentation.ui.views.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crosoftentechnicalchallenge.data.repository.UserRegisterRepository
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.data.utils.helpers.UserPersistence
import com.example.crosoftentechnicalchallenge.presentation.ui.state.RegisterUserState
import com.example.crosoftentechnicalchallenge.presentation.ui.state.toUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository = UserRegisterRepository(
        RetrofitService.getServiceInstace()
    )

    private val _registerUserState = MutableStateFlow<RegisterUserState>(RegisterUserState())
    val registerUserState = _registerUserState.asStateFlow()

    init {
        updateRegisterState()
    }

    private fun updateRegisterState() {
        _registerUserState.update { state ->
            state.copy(
                onChangedUserName = {
                    _registerUserState.value = _registerUserState.value.copy(userName = it)
                },
                onChangedUserPassword = {
                    _registerUserState.value = _registerUserState.value.copy(userPassword = it)
                },
                onChangedUserEmail = {
                    _registerUserState.value = _registerUserState.value.copy(userEmail = it)
                },
                onChangedConfirmPassword = {
                    _registerUserState.value = _registerUserState.value.copy(confirmPassword = it)
                },
            )
        }
    }


    fun registerUser() {
        viewModelScope.launch {
            val user = _registerUserState.value.toUser()
            val response = repository.registerUser(user)

            UserPersistence.setUser(user)

            if (response.isSuccessful) {
                val body = response.body()
                Log.d("User cadastrado com sucesso", body.toString())
            } else {
                Log.d("User", "Erro: ${response.code()} - ${response.message()}")
                val errorBody = response.errorBody()?.string()
                Log.d("User", "Erro no corpo: $errorBody")
            }
        }
    }


}
package com.example.crosoftentechnicalchallenge.presentation.ui.views.bookregister

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil3.Uri
import com.example.crosoftentechnicalchallenge.data.models.UploadResponse
import com.example.crosoftentechnicalchallenge.data.repository.BooksRepository
import com.example.crosoftentechnicalchallenge.data.utils.RetrofitService
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.presentation.ui.state.RegisterBookUIState
import com.example.crosoftentechnicalchallenge.presentation.ui.state.toBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import java.io.FileOutputStream

class BookRegisterViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    private val repository = BooksRepository(
        RetrofitService.getServiceInstace()
    )

    private val _state = MutableStateFlow<Boolean>(true)
    val state = _state.asStateFlow()

    private val _imageUri: MutableLiveData<android.net.Uri?> = MutableLiveData(null)

    fun setImageUri(uri: android.net.Uri) {
        _imageUri.value = uri
    }

    private val _form = MutableStateFlow(RegisterBookUIState())
    val form = _form.asStateFlow()

    init {
        handleFormChange()
    }

    private fun handleFormChange() {
        _form.update { state ->
            state.copy(
                onTitleChange = {
                    _form.value = _form.value.copy(title = it)
                },
                onSummaryChange = {
                    _form.value = _form.value.copy(summary = it)
                },
                onAuthorChange = {
                    _form.value = _form.value.copy(author = it)
                },
                onCategoryChange = {
                    _form.value = _form.value.copy(category = it)
                },
                onImageChange = {
                    _form.value = _form.value.copy(imageURI = it)
                }
            )
        }
    }

    @SuppressLint("Recycle")
    private fun requireImageFile(): MultipartBody.Part? {
        val uri = _imageUri.value ?: return null
        val filesDir = app.filesDir
        val file = File(filesDir, "image.png")
        app.contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        val requestBody = file.asRequestBody("image/png".toMediaType())
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }

    fun uploadBook() {
        val token = PreferencesHelper.getToken(app)
        if (token.isNullOrEmpty()) {
            Log.d("Erro de autenticação", "Token não encontrado")
            _state.value = false
            return
        }

        viewModelScope.launch {
            val bearer = "Bearer $token"
            val imagePart = requireImageFile()

            if (imagePart != null) {
                val imageUrlResponse = repository.uploadBookImage(bearer, imagePart)
                val imageUrl = if (imageUrlResponse.isSuccessful) {
                    imageUrlResponse.body()?.url ?: ""
                } else {
                    Log.d("Erro ao fazer upload da imagem", imageUrlResponse.errorBody()?.string() ?: "Erro desconhecido")
                    _state.value = false
                    return@launch
                }

                val bookRequest = form.value.toBook().copy(imageUrl = imageUrl)
                val response = repository.uploadBook(bearer, bookRequest)
                Log.d("Resposta", response.toString())

                if (response.isSuccessful) {
                    Log.d("Sucesso ao cadastrar livro", "${response.body()}")
                    _state.value = true
                } else {
                    Log.d("Erro ao cadastrar livro", response.errorBody()?.string() ?: "Erro desconhecido")
                    _state.value = false
                }
            } else {
                Log.d("Erro", "Não foi possível criar a parte da imagem")
                _state.value = false
            }
        }
    }
}

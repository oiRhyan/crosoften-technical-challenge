package com.example.crosoftentechnicalchallenge.presentation.ui.state

import com.example.crosoftentechnicalchallenge.data.models.Book
import com.example.crosoftentechnicalchallenge.data.models.Category

data class RegisterBookUIState (
    val title: String = "",
    val summary: String? = "",
    val author: String = "",
    val category: String = "",
    val imageURI: String = "",
    val onTitleChange: (String) -> Unit = {},
    val onSummaryChange: (String) -> Unit = {},
    val onAuthorChange: (String) -> Unit = {},
    val onCategoryChange: (String) -> Unit = {},
    val onImageChange: (String) -> Unit = {}
)


data class BookRegister(
    val title: String,
    val summary: String?,
    val author: String,
    val categoryId: Int,
    val imageUrl: String
)

fun RegisterBookUIState.toBook(url: String? = null): BookRegister {

    return BookRegister(
        title = title,
        summary = summary,
        author = author,
        categoryId = category.toInt(),
        imageUrl = url ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png"
    )
}

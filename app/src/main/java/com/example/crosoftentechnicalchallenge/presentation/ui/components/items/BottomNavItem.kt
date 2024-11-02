package com.example.crosoftentechnicalchallenge.presentation.ui.components.items

import androidx.annotation.DrawableRes
import com.example.crosoftentechnicalchallenge.R

sealed class BottomNavItem (
    @DrawableRes val icon : Int,
    @DrawableRes val iconSelected : Int
) {

    data object Home : BottomNavItem(
        icon = R.drawable.home_smile_line,
        iconSelected = R.drawable.home_smile_fill
    )

    data object Details : BottomNavItem(
        icon = R.drawable.book_shelf_line,
        iconSelected = R.drawable.book_shelf_fill
    )

    data object Register : BottomNavItem(
        icon = R.drawable.git_repository_commits_line,
        iconSelected = R.drawable.git_repository_commits_fill

    )

    data object Profile : BottomNavItem(
        icon = R.drawable.contacts_book_line,
        iconSelected = R.drawable.contacts_book_fill
    )

    companion object {
        val itens = listOf(Home, Details, Register, Profile)
    }

}
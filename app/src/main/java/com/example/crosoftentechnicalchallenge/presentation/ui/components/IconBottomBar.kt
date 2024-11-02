package com.example.crosoftentechnicalchallenge.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.crosoftentechnicalchallenge.presentation.ui.components.items.BottomNavItem

@Composable
fun RowScope.IconBottomBar(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        modifier = Modifier.padding(5.dp),
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = if (selected) item.iconSelected else item.icon),
                contentDescription = "icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Color.Black,
        )
    )
}
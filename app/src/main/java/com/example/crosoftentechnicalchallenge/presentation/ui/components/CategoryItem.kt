package com.example.crosoftentechnicalchallenge.presentation.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crosoftentechnicalchallenge.data.models.Category

@Composable
fun CategoryItem(item: Category) {
    OutlinedCard(
        modifier = Modifier.padding(1.dp),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.outlinedCardColors()
    ) {
        Text(
            text = item.title,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(item = Category("", 0, "Romance", ""))
}

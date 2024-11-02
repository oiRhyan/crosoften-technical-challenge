package com.example.crosoftentechnicalchallenge.presentation.ui.views.bookregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crosoftentechnicalchallenge.presentation.ui.components.UploadForm

@Composable
fun BookRegisterScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        Text(
            modifier = Modifier.padding(16.dp, 25.dp, 16.dp, 0.dp),
            text = "Registro", style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(4.dp))
        UploadForm()
    }
}
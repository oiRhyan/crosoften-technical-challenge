package com.example.crosoftentechnicalchallenge.presentation.ui.components

import android.app.Application
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.crosoftentechnicalchallenge.R
import com.example.crosoftentechnicalchallenge.data.factories.BookRegisterViewModelFactory
import com.example.crosoftentechnicalchallenge.presentation.ui.views.bookregister.BookRegisterViewModel

@Composable
fun UploadForm() {

    val context = LocalContext.current

    val viewModel : BookRegisterViewModel = viewModel(
        factory = BookRegisterViewModelFactory(context.applicationContext as Application)
    )

    val state by viewModel.state.collectAsState()

    val form by viewModel.form.collectAsState()


    val painter = rememberAsyncImagePainter(
        form.imageURI.ifEmpty { R.drawable.baseline_image_search_24 }
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            viewModel.setImageUri(uri!!)
            form.onImageChange(uri.toString())
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .border(2.dp, Color.Gray)
                .clickable {
                    launcher.launch("image/*")
                }
                .clip(RoundedCornerShape(200.dp))
            ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = "Image Search",
                contentScale = ContentScale.FillBounds
                )
        }

        OutlinedTextField(
            value = form.title,
            onValueChange = { form.onTitleChange(it)},
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = form.summary ?: "",
            onValueChange = { form.onSummaryChange(it) },
            label = { Text("Sumário") },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = form.author,
            onValueChange = { form.onAuthorChange(it) },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = form.category,
            onValueChange = { form.onCategoryChange(it) },
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                viewModel.uploadBook()
                if(!state) {
                    Toast.makeText(context, "Erro ao cadastrar livro", Toast.LENGTH_SHORT).show()
                }
                      },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Enviar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UploadFormPreview() {
    UploadForm()
}

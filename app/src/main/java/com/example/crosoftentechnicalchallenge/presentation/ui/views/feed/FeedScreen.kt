package com.example.crosoftentechnicalchallenge.presentation.ui.views.feed

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.crosoftentechnicalchallenge.R
import com.example.crosoftentechnicalchallenge.data.factories.FeedViewModelFactory
import com.example.crosoftentechnicalchallenge.presentation.ui.components.BookCard
import com.example.crosoftentechnicalchallenge.presentation.ui.components.CategoryItem
import com.example.crosoftentechnicalchallenge.presentation.ui.state.FeedScreenUIState

@Composable
fun FeedScreen() {

    val context = LocalContext.current

    val viewModel : FeedViewModel = viewModel(
        factory = FeedViewModelFactory(context.applicationContext as Application)
    )

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(16.dp, 25.dp, 16.dp, 0.dp),
            text = "Inicio", style = MaterialTheme.typography.displaySmall
        )
        when(state){
            is FeedScreenUIState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is FeedScreenUIState.Success -> {
                val books = (state as FeedScreenUIState.Success).books
                if(books.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.height(60.dp),
                            painter = painterResource(id = R.drawable.book_shelf_line),
                            contentDescription = "Empty State",
                            alignment = Alignment.Center
                        )
                        Text(
                            text = "Ops! Parece que você não possuí nenhum livro cadastrado",
                            color = Color.Black,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(books) { book ->
                            CategoryItem(item = book.category)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(books) { book ->
                            BookCard(book = book, app = context)
                        }
                    }
                }
            }
            is FeedScreenUIState.Error -> {
                val message = (state as FeedScreenUIState.Error).message
                Text(text = message)
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen()
}
package com.example.crosoftentechnicalchallenge.presentation.ui.views.details

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crosoftentechnicalchallenge.R
import com.example.crosoftentechnicalchallenge.data.factories.SearchViewModelFactory
import com.example.crosoftentechnicalchallenge.presentation.ui.components.BookCard
import com.example.crosoftentechnicalchallenge.presentation.ui.state.SearchUIState

@Composable
fun SearchScreen() {
    val context = LocalContext.current
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModelFactory(context.applicationContext as Application))

    val search by viewModel.search.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp, 25.dp, 16.dp, 0.dp),
            text = "Explorar", style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = query.search,
                onValueChange = query.onSearch,
                trailingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Search icon")
                },
                label = { Text("Digite o nome do livro") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.searchBook() }) {
                Text("Buscar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (search) {
            is SearchUIState.Empty -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.height(60.dp),
                        painter = painterResource(id = R.drawable.git_repository_commits_line),
                        contentDescription = "Empty State",
                        alignment = Alignment.Center
                    )
                    Text(
                        text = "Encontre seu livro favorito aqui!",
                        color = Color.Black,
                    )
                }
            }
            is SearchUIState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is SearchUIState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items((search as SearchUIState.Success).books) { book ->
                        BookCard(book = book, app = context)
                    }
                }
            }
            is SearchUIState.Error -> {
                Text(
                    text = (search as SearchUIState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
package com.example.n.ui.FavList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteListScreen(favoriteViewModel: FavoriteViewModel = viewModel()) {
    val favoriteList = favoriteViewModel.favoriteList.collectAsState(initial = emptyList()).value

    Column {
        Text(text = "Favorite News")
        LazyColumn {
            items(favoriteList) { newsItem ->
                Text(text = newsItem.title)
            }
        }
    }
}
package com.example.n.ui.FavList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class NewsItem(val id: String, val title: String)

class FavoriteViewModel : ViewModel() {
    private val _favoriteList = MutableStateFlow<List<NewsItem>>(emptyList())
    val favoriteList: StateFlow<List<NewsItem>> = _favoriteList

    fun addFavorite(newsItem: NewsItem) {
        viewModelScope.launch {
            _favoriteList.value = _favoriteList.value + newsItem
        }
    }
}
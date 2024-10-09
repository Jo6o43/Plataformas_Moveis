package com.example.n.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.n.Models.Article
import com.example.n.Models.RowArticle
import com.example.n.R
import com.example.n.theme.TopNewsTheme
import java.util.Date

@Composable
fun HomeView(modifier: Modifier = Modifier) {

    var articles = arrayListOf(
        Article(
            "Artigo 1",
            "Informações incrivéis.",
            "https://wallpapers.com/images/featured/image-79gc4p3mqu7an848.jpg",
            "url",
            Date()
        ),
        Article(
            "Artigo 2",
            "Informações incrivéis.",
            "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg",
            "url",
            Date()
        ),
        Article(
            "Artigo 3",
            "Informações incrivéis.",
            "https://tinypng.com/images/social/website.jpg",
            "url",
            Date()
        ),
    )

    LazyColumn {
        itemsIndexed(
            items = articles,
        ) { index, article ->

            RowArticle(modifier = Modifier, article = article)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    TopNewsTheme {
        HomeView()
    }
}

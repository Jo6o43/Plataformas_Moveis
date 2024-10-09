package com.example.n.Models

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.n.R

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
    {
        article.urlToImg?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
            )

        } ?: run {
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "image article"
            )
        }
        Column {
            Text(text = article.title!!, style = MaterialTheme.typography.bodyLarge)
            Text(text = article.description!!, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Date", style = MaterialTheme.typography.bodySmall)
        }
    }
}
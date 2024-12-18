package com.example.shoppinglist.lists.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun ItemRowView(
    modifier: Modifier = Modifier,
    item: Item,
    onCheckedChange: () -> Unit = {}
) {
    Row(modifier = modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = item.name ?: ""
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.qtd?.toInt()?.toString() ?: "0"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ItemRomViewPreview(){
    ShoppingListTheme {
        ItemRowView(item = Item(
            docId = "",
            name = "Lápis",
            qtd = 2.0,
        )
        )
    }
}
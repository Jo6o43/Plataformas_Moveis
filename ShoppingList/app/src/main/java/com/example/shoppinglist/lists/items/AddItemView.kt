package com.example.shoppinglist.lists.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddItemView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    listId: String
) {
    val viewModel: AddItemViewModel = viewModel()
    val state = viewModel.state.value

    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("enter item name")
            },
            value = state.name,
            onValueChange = viewModel::onNameChange
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("enter item quantity")
            },
            value = state.quantity.toString(),
            onValueChange = {
                val quantity = it.toIntOrNull()
                if (quantity != null) {
                    viewModel.onQuantityChange(quantity)
                } else {
                    viewModel.onQuantityChange(0) // Default to 0 if input is invalid
                }
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.addItem(listId)
                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemViewPreview() {
    ShoppingListTheme {
        AddItemView(listId = "")
    }
}
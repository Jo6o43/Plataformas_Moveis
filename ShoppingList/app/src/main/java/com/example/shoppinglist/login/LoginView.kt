package com.example.shoppinglist.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.MainActivity
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {},
    navController: NavController = rememberNavController()
) {

    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                placeholder = {
                    Text("email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = {
                    Text("password")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row() {
                Button(
                    onClick = {
                        viewModel.onLoginClick {
                            onLoginSuccess()
                        }
                    },
                    content = {
                        Text("Login")
                    },
                    modifier = modifier.padding(16.dp).size(110.dp,50.dp)
                )
                Button(
                    onClick = { navController.navigate(MainActivity.Screen.Register.route) },
                    content = {
                        Text("Register")
                    },
                    modifier = modifier.padding(16.dp).size(110.dp,50.dp)

                )
                Spacer(modifier = Modifier.height(16.dp))
                if (state.error != null)
                    Text(state.error ?: "")
                if (state.isLoading)
                    CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    ShoppingListTheme {
        LoginView()
    }
}
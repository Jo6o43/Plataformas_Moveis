package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.example.shoppinglist.lists.AddListView
import com.example.shoppinglist.lists.ListListsView
import com.example.shoppinglist.lists.items.AddItemView
import com.example.shoppinglist.lists.items.ListItemsView
import com.example.shoppinglist.login.LoginView
import com.example.shoppinglist.login.RegistView
import com.example.shoppinglist.ui.theme.ShoppingListTheme

const val TAG = "ShoppingList"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {

                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Login.route
                    ) {
                        composable(Screen.Login.route) {
                            LoginView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                onLoginSuccess = {
                                    navController.navigate(Screen.Home.route)
                                }
                            )
                        }
                        composable(Screen.Register.route) {
                            RegistView(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                onRegistSuccess = {
                                    navController.navigate(Screen.Register.route)
                                })
                        }
                        composable(Screen.Home.route) {
                            ListListsView(
                                navController = navController
                            )
                        }
                        composable(Screen.AddList.route) {
                            AddListView(navController = navController)
                        }
                        composable(Screen.ListItems.route) {
                            val listId = it.arguments?.getString("listId")
                            ListItemsView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                listId = listId ?: ""
                            )
                        }
                        composable(Screen.AddItem.route) {
                            val itemId = it.arguments?.getString("itemId")
                            AddItemView(navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                //itemId = itemId?: ""
                            )
                        }
                    }

                    LaunchedEffect(Unit) {
                        val auth = Firebase.auth
                        val currentUser = auth.currentUser
                        if (currentUser != null) {
                            navController.navigate(Screen.Home.route)
                        }
                    }
                }
            }
        }
    }

    sealed class Screen(val route: String) {
        object Login : Screen("login")
        object Register : Screen("register")
        object Home : Screen("home")
        object AddList : Screen("add_list")
        object ListItems : Screen("list_items/{listId}")
        object AddItem : Screen("add_item/{listId}")
    }
}



package com.example.n

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.n.theme.TopNewsTheme
import com.example.n.ui.ArticleDetail
import com.example.n.ui.FavList.FavoriteListScreen
import com.example.n.ui.FavList.FavoriteViewModel
import com.example.n.ui.FavList.NewsItem
import com.example.n.ui.HomeView
import com.example.n.ui.ProfileScreen
import com.example.n.ui.SearchScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopNewsTheme {
                val navController = rememberNavController()
                val favoriteViewModel: FavoriteViewModel by viewModels()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Awesome News")
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                                    Icon(Icons.Filled.Home, contentDescription = "Home")
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(
                                    onClick = { navController.popBackStack() },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "GoBack"
                                    )
                                }
                                IconButton(
                                    onClick = { navController.navigate(Screen.Favorite.route) },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.List,
                                        contentDescription = "FavList"
                                    )
                                }
                                var isLiked by remember { mutableStateOf(false) }
                                IconButton(
                                    onClick = {
                                        if (currentRoute != Screen.Home.route) {
                                            val newsItem = NewsItem("1", "Sample News")
                                            if (!isLiked) {
                                                favoriteViewModel.addFavorite(newsItem)
                                                isLiked = true
                                            } else {
                                                // Optionally, you can implement a removeFavorite function
                                                // favoriteViewModel.removeFavorite(newsItem)
                                                isLiked = false
                                            }
                                        }
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    if (isLiked) {
                                        Icon(Icons.Filled.Favorite, contentDescription = "Like")
                                    } else {
                                        Icon(Icons.Filled.FavoriteBorder, contentDescription = "Like")
                                    }
                                }
                            }
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = Screen.ArticleDetail.route) {
                            val url = it.arguments?.getString("articleUrl")
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: ""
                            )
                        }
                        composable(route = Screen.Search.route) {
                            SearchScreen()
                        }
                        composable(route = Screen.Profile.route) {
                            ProfileScreen()
                        }
                        composable(route = Screen.Favorite.route) {
                            FavoriteListScreen(favoriteViewModel = favoriteViewModel)
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ArticleDetail : Screen("article_Detail/{articleUrl}")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object Favorite : Screen("favorite")
}

@Preview
@Composable
fun DefaultPreview() {
    TopNewsTheme {
        HomeView()
    }
}
package com.example.spacegame

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacegame.ui.theme.SpaceGameTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SpaceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    NavHost(navController = navController, startDestination = "game_start") {
                        composable(route = "game_start") {
                            GameHomeView(onPlayClick = { navController.navigate("game_screen") })
                        }
                        composable(route = "game_screen") {
                            GameScreenView()
                        }
                    }
                }
            }
        }
    }
}



package com.example.mge_vk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mge_vk.ui.theme.MGEVKTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MGEVKTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "OnboardScreen", builder = {
                    composable("HomeScreen"){
                        HomeScreen(navController)
                    }
                    composable("OnboardScreen"){
                        OnboardScreen(navController)
                    }
                })
                }
            }
        }
    }

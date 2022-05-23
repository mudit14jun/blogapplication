package com.mm.blogapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mm.blogapplication.navigation.NavigationItem
import com.mm.blogapplication.screens.details.DetailsScreen
import com.mm.blogapplication.screens.home.HomeScreen
import com.mm.blogapplication.ui.theme.BlogApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MyApp {

                        NavHost(
                            navController = navController,
                            startDestination = NavigationItem.Home.route
                        ) {

                            composable(NavigationItem.Home.route) {
                                HomeScreen(navController = navController)
                            }

                            composable(NavigationItem.DetailsScreen.route) {
                                DetailsScreen()
                            }

                        }

                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}











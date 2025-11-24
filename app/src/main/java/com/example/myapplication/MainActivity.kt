package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyApplicationTheme {

                val navController = rememberNavController()
                val recommendationViewModel: RecommendationViewModel = viewModel()

                Surface(color = MaterialTheme.colorScheme.background) {

                    Scaffold(
                        bottomBar = { BottomBar() }
                    ) { padding ->

                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(padding)
                        ) {

                            composable("home") {
                                HomeScreen(
                                    navController = navController,
                                    viewModel = recommendationViewModel
                                )
                            }

                            composable("recommandation") {
                                RecommandationScreen(navController,viewModel = recommendationViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

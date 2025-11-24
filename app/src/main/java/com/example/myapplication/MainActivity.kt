package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyApplicationTheme {

                val navController = rememberNavController()

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
                                    navController = navController
                                )
                            }

                            composable("recommandation") {
                                RecommandationScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

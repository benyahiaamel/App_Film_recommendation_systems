package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecommendationViewModel
) {
    var showMovies by remember { mutableStateOf(true) }

    Column(
        modifier = modifier   // ← on utilise le modifier reçu
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        SearchBar()
        FilterChips()
        CarouselBanner()

        TendanceTitle { expanded ->
            showMovies = expanded
        }

        if (showMovies) {
            MovieList(navController, viewModel)
        }
    }
}
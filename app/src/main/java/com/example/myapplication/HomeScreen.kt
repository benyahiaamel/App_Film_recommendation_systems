package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavController

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navController: NavController) {
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
            MovieList(navController)
        }
    }
}
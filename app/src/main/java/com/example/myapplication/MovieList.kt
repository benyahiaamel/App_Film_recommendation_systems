package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

fun getSampleMovies(): List<Movie> {
    return listOf(
        Movie(
            title = "Cruella",
            genre = "Crime-comédie",
            date = "13/11/2025",
            duration = "2h 23m",
            rating = 4.5f,
            image = "https://picsum.photos/id/1025/300/200"
        ),
        Movie(
            title = "Godzilla The Kong",
            genre = "Action/SF",
            date = "13/10/2025",
            duration = "1h 53m",
            rating = 4.0f,
            image = "https://picsum.photos/id/1011/300/200"
        ),
        Movie(
            title = "Bing Bang",
            genre = "Comédie",
            date = "01/09/2025",
            duration = "1h 45m",
            rating = 3.5f,
            image = "https://picsum.photos/id/1012/300/200"
        )
    )
}

@Composable
fun MovieList(navController: NavController) {
    val movies = getSampleMovies()

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie, navController)
        }
    }
}

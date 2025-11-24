package com.example.myapplication

import android.content.Context
import com.opencsv.CSVReader
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import org.json.JSONArray
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun getSampleMovies(context: Context): List<Movie> {
    val moviesList = mutableListOf<Movie>()
    val inputStream = context.assets.open("IMDB_db.csv")
    val reader = CSVReader(inputStream.bufferedReader())

    reader.skip(1)
    var index = 0

    reader.forEach { tokens ->
        try {
            if (tokens.size > 9) {
                val title = tokens[0]
                val image = tokens[1]
                val genre = tokens[5]
                val date = tokens[9]
                val description = tokens[2]
                val rating = tokens[11].toFloatOrNull() ?: 0f
                val duration = tokens[12]

                moviesList.add(
                    Movie(
                        id = index,
                        title = title,
                        rating = rating,
                        image = image,
                        duration = duration,
                        genre = genre,
                        date = date,
                        description = description
                    )
                )
                index++
            }
        } catch (_: Exception) {}
    }
    return moviesList
}


fun loadSparseKnnMatrix(context: Context): List<List<Int>> {
    val inputStream = context.assets.open("neighbors.json")
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val jsonArray = JSONArray(jsonString)

    val neighbors = mutableListOf<List<Int>>()

    for (i in 0 until jsonArray.length()) {
        val row = jsonArray.getJSONArray(i)
        neighbors.add(List(row.length()) { j -> row.getInt(j) })
    }
    return neighbors
}


@Composable
fun MovieList(
    navController: NavController,
    viewModel: RecommendationViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val movies = getSampleMovies(context)
    val neighbors = loadSparseKnnMatrix(context)

    viewModel.updateMoviesList(movies)

    var selectedMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        items(movies) { movie ->

            val isSelected = selectedMovies.contains(movie)

            MovieCard(
                movie = movie,
                selected = isSelected,
                onClick = {
                    selectedMovies =
                        if (isSelected) selectedMovies - movie
                        else selectedMovies + movie

                    if (selectedMovies.size == 5) {

                        viewModel.updateSelectedMovies(selectedMovies.map { it.id })
                        viewModel.computeRecommendations(neighbors)

                        navController.navigate("recommandation")
                    }
                }
            )
        }
    }
}

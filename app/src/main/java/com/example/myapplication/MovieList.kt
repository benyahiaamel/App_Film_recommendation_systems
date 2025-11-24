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

fun getSampleMovies(context: Context): List<Movie> {
    val moviesList = mutableListOf<Movie>()
    val inputStream = context.assets.open("IMDB_db.csv")
    val reader = CSVReader(inputStream.bufferedReader())
    reader.skip(1) // Skip header line
    var index = 0
    reader.forEach { tokens ->
        try {
            if (tokens.size > 9) {
                val title = tokens[0]
                val image = tokens[1]
                val genre = tokens[5]
                val date = tokens[9]
                var description = tokens[2]
                val rating = tokens[11].toFloatOrNull() ?: 0f
                val duration = tokens[12]
                moviesList.add(Movie(id = index, title = title, rating = rating, image = image, duration = duration, genre = genre, date = date, description = description))
                index++
            }
        } catch (e: Exception) {
            // Log or handle parsing errors for a specific line
        }
    }
    return moviesList
}

private fun loadSparseKnnMatrix(context: Context, filename: String = "neighbors.json"): List<List<Int>> {
    val inputStream = context.assets.open(filename)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val jsonArray = org.json.JSONArray(jsonString)
    val neighbors = mutableListOf<List<Int>>()

    for (i in 0 until jsonArray.length()) {
        val rowArray = jsonArray.getJSONArray(i)
        val rowList = mutableListOf<Int>()
        for (j in 0 until rowArray.length()) {
            rowList.add(rowArray.getInt(j))
        }
        neighbors.add(rowList)
    }
    return neighbors
}

@Composable
fun MovieList(navController: NavController, viewModel: RecommendationViewModel) {
    val context = LocalContext.current

    val movies = getSampleMovies(context)
    val neighbors: List<List<Int>> = loadSparseKnnMatrix(context)

    viewModel.updateMoviesList(movies)

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie, navController, neighbors, viewModel)
        }
    }
}

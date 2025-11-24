package com.example.myapplication


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommandationScreen(navController: NavController, viewModel: RecommendationViewModel) {

    // ✔️ ICI : remember à l'intérieur du composable
    var showDialog by remember { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf<SimilarMovie?>(null) }
    val movies by viewModel.moviesList.collectAsState()
    val recommendedIds by viewModel.recommendedMovieIds.collectAsState()

    fun sampleSimilarMovies(): List<SimilarMovie> {
        val recommendedMovies = recommendedIds.mapNotNull { id ->
            movies.find { it.id == id }
        }

        return recommendedMovies.take(5).map { movie -> SimilarMovie(
            id = movie.id,
            title = movie.title,
            genre = movie.genre,
            date = movie.date,
            duration = movie.duration,
            rating = movie.rating,
            posterUrl = movie.image,
            description = movie.description
        ) }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(top = 2.dp)   // ↑ remonte la barre
                    .fillMaxWidth(),
                title = { Text("Recommandations") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()    // ✔️ Retour à Home
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            Text(
                "Top 5 films similaires",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(sampleSimilarMovies()) { index, movie ->

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp).clickable{
                                selectedMovie = movie
                                showDialog = true
                            }
                    ) {
                        Text("${index + 1}", modifier = Modifier.padding(end = 12.dp))

                        AsyncImage(
                            model = movie.posterUrl,
                            contentDescription = movie.title,
                            modifier = Modifier
                                .width(90.dp)
                                .height(120.dp)
                        )

                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(movie.title, softWrap = false, overflow = TextOverflow.Ellipsis, maxLines = 1)
                            Text(movie.genre, color = Color.Red)
                            Text(movie.date)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(movie.rating.toInt() / 2) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "",
                                        tint = Color(0xFFFFD700)
                                    )
                                }
                            }
                        }

//                        Button(
//                            onClick = {
//                                selectedMovie = movie
//                                showDialog = true
//                            },
//                            modifier = Modifier.align(Alignment.CenterVertically)
//                        ) {
//                            Text("DÉTAILS", color = MaterialTheme.colorScheme.secondary)
//                        }
                    }
                }
            }

            // ✔️ Affichage du popup de description
            if (showDialog && selectedMovie != null) {
                MovieDescriptionDialog(
                    movie = selectedMovie!!,
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}

data class SimilarMovie(
    val id: Int,
    val title: String,
    val genre: String,
    val date: String,
    val duration: String,
    val rating: Float,
    val posterUrl: String,
    val description: String
)


@Composable
fun MovieDescriptionDialog(movie: SimilarMovie, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {

                Text("Titre : ${movie.title}")
                Spacer(Modifier.height(8.dp))

                Text("Genre : ${movie.genre}")
                Spacer(Modifier.height(8.dp))

                Text("Durée : ${movie.duration} min")
                Spacer(Modifier.height(8.dp))


                Text("Synopsis :")
                Text(
                    movie.description,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    )
}
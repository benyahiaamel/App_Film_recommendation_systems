package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment





import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommandationScreen(navController: NavController) {

    // ✔️ ICI : remember à l'intérieur du composable
    var showDialog by remember { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf<SimilarMovie?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 20.dp)   // ↑ remonte la barre
                    .fillMaxWidth(),
                title = { Text("Recommandation") },
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
                            .padding(12.dp)
                    ) {
                        Text("${index + 1}", modifier = Modifier.padding(end = 12.dp))

                        AsyncImage(
                            model = movie.posterUrl,
                            contentDescription = movie.title,
                            modifier = Modifier
                                .width(90.dp)
                                .height(120.dp)
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .weight(1f)
                        ) {
                            Text(movie.title, style = MaterialTheme.typography.bodyLarge)
                            Text(movie.genre, color = MaterialTheme.colorScheme.primary)
                            Text(movie.date)
                            Text("⭐ ${movie.rating}")
                            Text(movie.duration)
                        }

                        Button(
                            onClick = {
                                selectedMovie = movie
                                showDialog = true
                            },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text("DESCRIPTION")
                        }
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
    val title: String,
    val genre: String,
    val date: String,
    val duration: String,
    val rating: Float,
    val posterUrl: String
)

fun sampleSimilarMovies() = listOf(
    SimilarMovie("Maléfique", "Crime-comédie", "2014/2015", "2h20min", 4.5f, "https://picsum.photos/200/300?1"),
    SimilarMovie("The Devil Wears Prada", "Crime-comédie-drame", "2006/2007", "2h05min", 4.0f, "https://picsum.photos/200/300?2"),
    SimilarMovie("Marie Antoinette", "Crime-comédie", "2006/2007", "1h20min", 4.0f, "https://picsum.photos/200/300?3"),
    SimilarMovie("Ocean's 8", "Crime-comédie-drame", "2018/2019", "2h20min", 4.5f, "https://picsum.photos/200/300?4"),
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

                Text("Durée : ${movie.duration}")
                Spacer(Modifier.height(8.dp))

                Text("Réalisateur : Robert Stromberg")
                Spacer(Modifier.height(8.dp))

                Text("Synopsis :")
                Text(
                    "Le film raconte l'histoire de ${movie.title}, la célèbre méchante..."
                )
                Spacer(Modifier.height(8.dp))

                Text("Particularités :")
                Text("• Esthétique soignée, costumes travaillés…")
            }
        }
    )
}
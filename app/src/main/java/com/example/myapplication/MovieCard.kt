package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MovieCard(movie: Movie) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(
                12.dp,
                RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.12f),
                spotColor = Color.Black.copy(alpha = 0.20f)
            )
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ⭐ Poster
            Box(
                modifier = Modifier
                    .size(width = 120.dp, height = 90.dp).shadow(
                        10.dp,
                        RoundedCornerShape(16.dp),
                        ambientColor = Color.Black.copy(alpha = 0.15f),
                        spotColor = Color.Black.copy(alpha = 0.25f)
                    )
            ) {
                // 1. Draw the image first, so it's at the bottom of the stack.
                AsyncImage(
                    model = movie.image,
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // 2. Draw the gradient overlay ON TOP of the image.
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.45f)
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // ⭐ Infos + ❤️ Like
            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    // ❤️ Button à droite du texte
                    LikeButton()
                }

                Text(
                    text = movie.genre,
                    color = Color(0xFFB81D24),
                    fontWeight = FontWeight.SemiBold
                )

                Text(text = movie.date, color = Color.Gray)
                Text(text = movie.duration, color = Color.Gray)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(movie.rating.toInt()) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "",
                            tint = Color(0xFFFFD700)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MovieCard(movie: Movie, navController: NavController, neighbors: List<List<Int>>, viewModel: RecommendationViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                val neighborsIds = neighbors[movie.id]
                viewModel.updateRecommendedMovies(neighborsIds)
                navController.navigate("recommandation")
            }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = movie.image,
            contentDescription = movie.title,
            modifier = Modifier.width(90.dp)
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
    }
}
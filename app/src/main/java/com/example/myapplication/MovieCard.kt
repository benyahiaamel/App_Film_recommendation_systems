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
fun MovieCard(
    movie: Movie,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                if (selected) Color(0x3327AE60) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        AsyncImage(
            model = movie.image,
            contentDescription = movie.title,
            modifier = Modifier
                .width(90.dp)
                .height(110.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                movie.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(movie.genre, color = Color.Red)
            Text(movie.date)
            Text(movie.duration)

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
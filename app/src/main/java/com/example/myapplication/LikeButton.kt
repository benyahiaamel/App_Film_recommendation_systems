package com.example.myapplication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

@Composable
fun LikeButton() {

    var liked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (liked) 1.3f else 1f,
        animationSpec = tween(durationMillis = 250),
        label = ""
    )

    IconButton(onClick = { liked = !liked }) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Like",
            tint = if (liked) Color.Red else Color.White,
            modifier = Modifier.scale(scale)
        )
    }
}

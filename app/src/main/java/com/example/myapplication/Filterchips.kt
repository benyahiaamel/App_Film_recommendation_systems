package com.example.myapplication

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth


@Composable
fun FilterChips() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        FilterChipMenu(
            label = "Genre",
            options = listOf("Action", "Comédie", "Crime", "SF", "Drama"),
            onSelected = {}
        )

        FilterChipMenu(
            label = "Année",
            options = listOf("2025", "2024", "2023", "2022"),
            onSelected = {}
        )

        FilterChipMenu(
            label = "Note",
            options = listOf("5 ⭐", "4 ⭐", "3 ⭐", "2 ⭐", "1 ⭐"),
            onSelected = {}
        )

        FilterChipMenu(
            label = "Durée",
            options = listOf("1h", "1h30", "2h", "2h30"),
            onSelected = {}
        )
    }
}

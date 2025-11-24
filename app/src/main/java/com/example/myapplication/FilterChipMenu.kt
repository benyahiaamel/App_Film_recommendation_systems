package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.material.icons.filled.ArrowDropDown

@Composable
fun FilterChipMenu(
    label: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(label) }
    Box {
    OutlinedButton(
        onClick = { expanded = true },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier
            .widthIn(min = 80.dp)
            .height(35.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                selectedText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        options.forEach { opt ->
            DropdownMenuItem(
                text = { Text(opt) },
                onClick = {
                    selectedText = opt
                    expanded = false
                    onSelected(opt)
                }
            )
        } }
    }
}

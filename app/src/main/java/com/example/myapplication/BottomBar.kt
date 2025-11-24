package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.CardDark

@Composable
fun BottomBar() {

    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = CardDark
    ) {

        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            },
            label = {
                Text("Home", color = Color.White)
            }
        )

//        NavigationBarItem(
//            selected = selectedIndex == 1,
//            onClick = { selectedIndex = 1 },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search",
//                    tint = Color.White
//                )
//            },
//            label = {
//                Text("Search", color = Color.White)
//            }
//        )

        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            },
            label = {
                Text("Favoris", color = Color.White)
            }
        )

//        NavigationBarItem(
//            selected = selectedIndex == 3,
//            onClick = { selectedIndex = 3 },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Notifications,
//                    contentDescription = "Notifications",
//                    tint = Color.White
//                )
//            },
//            label = {
//                Text("Notif", color = Color.White)
//            }
//        )

        NavigationBarItem(
            selected = selectedIndex == 4,
            onClick = { selectedIndex = 4 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            },
            label = {
                Text("Profil", color = Color.White)
            }
        )
    }
}

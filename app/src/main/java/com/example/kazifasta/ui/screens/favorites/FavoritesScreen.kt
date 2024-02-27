package com.example.kazifasta.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.theme.dark
import com.example.kazifasta.ui.theme.mateWhite

@Composable
fun FavoritesScreen(){
    Scaffold (
        containerColor = mateWhite,
        topBar = { AppBar(onClick = { /*TODO*/ }, title = "Favorites", backButton = false) },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Favorites Screen", color = dark)
            }
        }
    )
}
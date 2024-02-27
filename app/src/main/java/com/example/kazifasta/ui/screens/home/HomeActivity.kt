package com.example.kazifasta.ui.screens.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.kazifasta.MainActivity
import com.example.kazifasta.authViewModel
import com.example.kazifasta.database
import com.example.kazifasta.ui.navigation.BottomNavigationBar
import com.example.kazifasta.ui.theme.dark

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))
        super.onCreate(savedInstanceState)
        database.fetchProfiles()

        setContent {
            if(authViewModel.isUserLoggedIn(this)){
                HomeContent()
            }else{
                val navigateToMain = Intent(
                    this@HomeActivity,
                    MainActivity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(navigateToMain)

                finish()
            }
        }
    }
}

@Composable
fun HomeContent() {
//    var isLoading by remember { mutableStateOf(true) }

//    LaunchedEffect(key1 = isLoading) {
//        delay(2500)
//        isLoading = false
//        println("HOME PROFILES: ${database.profilesList}")
//    }

    if (database.isLoading) {
        LoadingScreen()
    } else {
        if (database.profilesList.isNotEmpty()) {
            BottomNavigationBar()
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(dark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

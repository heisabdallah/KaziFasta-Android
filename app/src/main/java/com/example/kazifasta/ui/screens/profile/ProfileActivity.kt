package com.example.kazifasta.ui.screens.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.kazifasta.data.model.Profile
import com.example.kazifasta.ui.theme.KaziFastaTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))

        super.onCreate(savedInstanceState)
        setContent {
            KaziFastaTheme {

                val profile = intent.getParcelableExtra<Profile>("Profile")

                if (profile != null){
                    ProfileScreen(profile = profile) { super.onBackPressed() }
                }


            }
        }
    }
}

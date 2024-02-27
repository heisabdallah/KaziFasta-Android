package com.example.kazifasta.ui.screens.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kazifasta.ui.screens.settings.sections.GeneralSection
import com.example.kazifasta.ui.screens.settings.sections.HelpNSupport
import com.example.kazifasta.ui.screens.settings.sections.LanguageNRegion
import com.example.kazifasta.ui.screens.settings.sections.NotificationsNSounds
import com.example.kazifasta.ui.screens.settings.sections.PrivacyNSecurity
import com.example.kazifasta.ui.theme.KaziFastaTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))
        super.onCreate(savedInstanceState)

        setContent {
            KaziFastaTheme {
                val navController = rememberNavController()
//                val viewModel = SupabaseAuthViewModel()
                val context = LocalContext.current

                NavHost(
                    navController = navController,
                    startDestination = "Settings",
                    enterTransition = {
                        EnterTransition.None
                    },
                    exitTransition = {
                        ExitTransition.None
                    }
                ) {
                    composable("Settings") { SettingsScreen(onBackPressed = { super.onBackPressed() }, navController) }
                    composable(SettingsRoute.GENERAL) { GeneralSection(onBackPressed = { navController.popBackStack() }) }
                    composable(SettingsRoute.NOTIFICATION_SOUNDS) { NotificationsNSounds(onBackPressed = { navController.popBackStack() }) }
                    composable(SettingsRoute.PRIVACY_SECURITY) { PrivacyNSecurity(onBackPressed = { navController.popBackStack() }) }
                    composable(SettingsRoute.LANGUAGE_REGION) { LanguageNRegion(onBackPressed = { navController.popBackStack() }) }
                    composable(SettingsRoute.HELP_SUPPORT) { HelpNSupport(onBackPressed = { navController.popBackStack() }) }
                }

            }
        }
    }
}

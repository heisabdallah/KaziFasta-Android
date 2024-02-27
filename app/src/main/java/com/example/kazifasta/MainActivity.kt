package com.example.kazifasta

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kazifasta.ui.navigation.NavigationTransitions
import com.example.kazifasta.ui.screens.auth.AuthViewModel
import com.example.kazifasta.ui.screens.auth.LoginScreen
import com.example.kazifasta.ui.screens.auth.RegisterScreen
import com.example.kazifasta.ui.screens.auth.theme.AuthActivityTheme
import com.example.kazifasta.ui.screens.home.HomeActivity
import com.example.kazifasta.ui.viewmodel.SupabaseAuthViewModel
import com.example.kazifasta.ui.viewmodel.SupabaseDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint

val database = SupabaseDatabaseViewModel()
val authVM = AuthViewModel()
var authViewModel: SupabaseAuthViewModel = SupabaseAuthViewModel()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    var authViewModel: SupabaseAuthViewModel = SupabaseAuthViewModel()
    private var doubleBackPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))

        println("IS LOGGED IN?: ${authViewModel.isUserLoggedIn(this)}")
        println("IS CURRENT SESSEION?: ${authVM.currentSession.value}")

        // Check if the user is logged in
        if (authViewModel.isUserLoggedIn(this)) {
            // User is logged in, navigate to HomeActivity
            val navigateToHome = Intent(this, HomeActivity::class.java)
            startActivity(navigateToHome)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finish()
        } else {
            setContent {
                AuthActivityTheme {
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(
                        navController = navController,
                        startDestination = "Login",
                        enterTransition = {
                            NavigationTransitions.scaleIntoContainer()
                        },
                        exitTransition = {
                            NavigationTransitions.scaleOutOfContainer()
                        }
                    ) {
                        composable("Login") { LoginScreen(navController, authViewModel, context) }
                        composable("Register") { RegisterScreen(navController, authViewModel, context) }
                    }
                }
            }
        }

    }

    @Deprecated("Deprecated in Java")
    @Suppress("MissingSuperCall")
    override fun onBackPressed() {
        if (doubleBackPressedOnce) {
            // If the back button is pressed twice within a short time, exit the application
            super.onBackPressed()
            finish()
        } else {
            doubleBackPressedOnce = true
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()

            // Reset the doubleBackPressedOnce flag after a short delay
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackPressedOnce = false
            }, DOUBLE_BACK_PRESS_DELAY)
        }
    }

    companion object {
        private const val DOUBLE_BACK_PRESS_DELAY = 2000L // Time in milliseconds
    }
}
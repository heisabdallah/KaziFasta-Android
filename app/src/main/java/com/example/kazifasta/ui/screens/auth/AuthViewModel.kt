package com.example.kazifasta.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.gotrue.user.UserSession

class AuthViewModel: ViewModel() {
    val currentSession = mutableStateOf<UserSession?>(null)
    var isLoading by mutableStateOf(true)

    var fNameText by mutableStateOf("")
    var lNameText by mutableStateOf("")
    var emailText by mutableStateOf("")
    var passwordText by mutableStateOf("")
    var confirmPasswordText by mutableStateOf("")
}
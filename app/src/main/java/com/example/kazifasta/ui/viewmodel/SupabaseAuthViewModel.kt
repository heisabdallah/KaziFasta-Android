package com.example.kazifasta.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazifasta.authVM
import com.example.kazifasta.data.model.UserState
import com.example.kazifasta.data.network.SupabaseClient.supabase
import com.example.kazifasta.ui.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SupabaseAuthViewModel @Inject constructor(): ViewModel() {

    private var _userState = MutableStateFlow<UserState>(UserState.Idle)
    var userState: Flow<UserState> = _userState
    var currentSession = mutableStateOf<UserSession?>(null)



    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabase.gotrue.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                authVM.currentSession.value = supabase.gotrue.currentSessionOrNull()
                currentSession.value = supabase.gotrue.currentSessionOrNull()
                _userState.value = UserState.Success("Registered successfully!")
            } catch(e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = supabase.gotrue.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }

    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabase.gotrue.loginWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                authVM.currentSession.value = supabase.gotrue.currentSessionOrNull()
                currentSession.value = supabase.gotrue.currentSessionOrNull()
                _userState.value = UserState.Success("Logged in successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    fun logout(context: Context) {
        val sharedPref = SharedPreferenceHelper(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                supabase.gotrue.logout()
                sharedPref.clearPreferences()
                authVM.currentSession.value = null
                currentSession.value = null
              _userState.value = UserState.Idle
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun isUserLoggedIn(context: Context): Boolean {
        val token = getToken(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                if(token.isNullOrEmpty()) {
                    _userState.value = UserState.Idle
                } else {
                    supabase.gotrue.retrieveUser(token)
                    supabase.gotrue.refreshCurrentSession()
                    saveToken(context)
                    authVM.currentSession.value = supabase.gotrue.currentSessionOrNull()
                    currentSession.value = supabase.gotrue.currentSessionOrNull()
                    _userState.value = UserState.Success("User already logged in!")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
        return !token.isNullOrEmpty()
    }


}
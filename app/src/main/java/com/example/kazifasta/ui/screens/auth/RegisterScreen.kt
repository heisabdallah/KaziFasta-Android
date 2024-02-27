package com.example.kazifasta.ui.screens.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.SoundEffectConstants
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kazifasta.R
import com.example.kazifasta.authVM
import com.example.kazifasta.data.model.UserState
import com.example.kazifasta.ui.screens.auth.components.CustomButton
import com.example.kazifasta.ui.screens.auth.components.CustomTextField
import com.example.kazifasta.ui.screens.auth.components.CustomTextField2
import com.example.kazifasta.ui.screens.home.HomeActivity
import com.example.kazifasta.ui.theme.dark
import com.example.kazifasta.ui.theme.gray
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens
import com.example.kazifasta.ui.viewmodel.SupabaseAuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, authViewModel: SupabaseAuthViewModel, context: Context) {
    val haptic = LocalHapticFeedback.current
    val view = LocalView.current

    var snackbarMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val userState = authViewModel.userState

    fun handleClickBehaviour() {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        view.playSoundEffect(SoundEffectConstants.CLICK)
    }

    fun handleClickRegisterCTA() {
        handleClickBehaviour()
        println("FirstName: ${authVM.fNameText}, LastName: ${authVM.lNameText}, Email: ${authVM.emailText}, password: ${authVM.passwordText}, confirmpassword: ${authVM.confirmPasswordText}")

        CoroutineScope(Dispatchers.IO).launch {
            authViewModel.signUp(
                context = context,
                userEmail = authVM.emailText,
                userPassword = authVM.passwordText
            )
        }
    }

    fun navigateToLoginScreen() {
        handleClickBehaviour()
        navController.popBackStack()
    }

    Scaffold(
        containerColor = green,
        snackbarHost = {
            if (snackbarMessage.isNotEmpty()) {
                Snackbar(
                    modifier = Modifier
                        .padding(horizontal = Dimens.normalPadding * 2)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    containerColor = mateWhite,
                    action = {
                        TextButton(onClick = { snackbarMessage = "" }) {
                            Text(text = "Dismiss", color = green)
                        }
                    }
                ) {
                    Text(text = snackbarMessage, color = dark)
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    Dimens.normalPadding
                )
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "",
                    Modifier.size(100.dp),
                    tint = mateWhite
                )
                Text(text = stringResource(id = R.string.register_welcome_message), color = gray)
                Column(
                    Modifier.padding(Dimens.normalPadding),
                    verticalArrangement = Arrangement.spacedBy(
                        Dimens.smallPadding * 2
                    )
                ) {
                    CustomTextField2(authVM = authVM, keyboardType = "fName")
                    CustomTextField(authVM = authVM, keyboardType = "email")
                    CustomTextField(authVM = authVM, keyboardType = "password")
                    CustomTextField(authVM = authVM, keyboardType = "confPassword")

                    // Use a Flow to observe userState changes
                    LaunchedEffect(authViewModel.userState) {
                        authViewModel.userState.onEach { state ->
                            isLoading = (state == UserState.Loading)

                            when (state) {
                                is UserState.Success -> {
                                    snackbarMessage = "Account Created successful!"
                                    val navigateToHome = Intent(context, HomeActivity::class.java)
                                    context.startActivity(navigateToHome)
                                    // Finish the current activity
                                    (context as? Activity)?.finish()
                                }

                                is UserState.Error -> {
                                    // Show an error message
                                    snackbarMessage = state.message
                                }

                                else -> {
                                    // UserState.Idle or other states
                                }
                            }
                        }.collect()
                    }

                    CustomButton(
                        onClick = { handleClickRegisterCTA() },
                        label = stringResource(id = R.string.button_signUp),
                        userState
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.smallPadding)) {
                    Text(text = stringResource(id = R.string.prompt_signUp), color = gray)
                    Text(
                        text = stringResource(id = R.string.button_signIn),
                        color = mateWhite,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { navigateToLoginScreen() }
                    )
                }
            }
        }
    )
}

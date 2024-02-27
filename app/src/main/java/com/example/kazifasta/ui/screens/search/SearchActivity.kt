package com.example.kazifasta.ui.screens.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.kazifasta.MainActivity
import com.example.kazifasta.authViewModel
import com.example.kazifasta.data.model.UserState
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.common.SearchBar
import com.example.kazifasta.ui.screens.auth.components.CustomButton
import com.example.kazifasta.ui.theme.KaziFastaTheme
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

//    @Inject
//    var authViewModel: SupabaseAuthViewModel = SupabaseAuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(SystemBarStyle.dark(1))
        super.onCreate(savedInstanceState)

        setContent {
            KaziFastaTheme {

                val context = LocalContext.current

                val userState = authViewModel.userState


                fun handleClickLogoutCTA() {
                    CoroutineScope(Dispatchers.IO).launch {
                        authViewModel.logout(context)

                        // Observe the userState changes using Flow
                        authViewModel.userState.collect { newState ->
                            if (newState == UserState.Idle) {
                                // The userState is now Idle, navigate to AuthActivity
                                withContext(Dispatchers.Main) {
                                    val navigateToMain = Intent(this@SearchActivity, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    startActivity(navigateToMain)
                                    finish()
                                }
                            }
                        }
                    }
                }







                Scaffold(
                    containerColor = mateWhite,
                    topBar = {
                        AppBar(onClick = { super.onBackPressed() })
                    },
                    content = { innerPadding ->
                        //         Main - Scrollable
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
//                            .background(mateBlack)
                                .verticalScroll(state = rememberScrollState())
                        ) {
                            //             Top Section
                            Column(
                                modifier = Modifier
//                                    .background(color = mateBlack)
                                    .padding(horizontal = Dimens.normalPadding),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(Dimens.normalPadding * 2))
                                Text(text = "Search Everywhere!", color = mateBlack)
                                Spacer(modifier = Modifier.height(Dimens.normalPadding))
                                //            Search Bar
                                SearchBar()

                                Spacer(modifier = Modifier.height(12.dp))
                                CustomButton(onClick = { handleClickLogoutCTA() }, label = "Log Out", userState = userState)

                                Text("User: ${authViewModel.currentSession.value?.user?.email}", color = mateBlack)

                            }
                        }
                    }
                )

            }
        }
    }
}

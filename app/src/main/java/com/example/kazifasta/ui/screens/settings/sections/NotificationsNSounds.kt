package com.example.kazifasta.ui.screens.settings.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.kazifasta.R
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.theme.dark

@Composable
fun NotificationsNSounds(onBackPressed: () -> Unit){
    Scaffold (
        containerColor = dark,
        topBar = { AppBar(onClick = { onBackPressed.invoke() }, title = stringResource(id = R.string.title_notification_n_sounds)) },
        content = {innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.title_notification_n_sounds))
            }
        }
    )
}
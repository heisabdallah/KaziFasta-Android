package com.example.kazifasta.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kazifasta.R
import com.example.kazifasta.ui.common.AppBar
import com.example.kazifasta.ui.screens.profile.components.SettingsTitle
import com.example.kazifasta.ui.screens.settings.model.SettingsList
import com.example.kazifasta.ui.theme.KaziFastaTheme
import com.example.kazifasta.ui.theme.dark
import com.example.kazifasta.ui.theme.mateBlack

@Composable
fun SettingsScreen(onBackPressed: () -> Unit, navController: NavController){

    Scaffold (
        containerColor = dark,
        topBar = {
            AppBar(onClick = { onBackPressed.invoke() }, title = stringResource(id = R.string.title_settings))
        },
        content = {innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                SettingsList.forEach { setting ->
                    Divider(thickness = 0.2.dp, color = mateBlack)
                    SettingsTitle(title = setting.title, description = setting.description, onClick = {navController.navigate(setting.route)})
                }
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    KaziFastaTheme {
        SettingsScreen(onBackPressed = {}, navController = rememberNavController())
    }
}
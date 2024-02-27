package com.example.kazifasta.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kazifasta.ui.theme.dark
import com.example.kazifasta.ui.theme.mateWhite
import com.example.kazifasta.ui.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(title: String, color: Color = dark, onClick: () -> Unit){
    TopAppBar(
        modifier = Modifier.fillMaxHeight(0.108f),
        colors = topAppBarColors(containerColor = color),
        title = {
            Row(
                modifier = Modifier.padding(top = Dimens.bigPadding),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = mateWhite)
            }
        },
        actions = {
            Button(onClick = { onClick.invoke()}, colors = ButtonDefaults.buttonColors(dark)) {
                Icon(Icons.Rounded.Settings, contentDescription = null)
            }
        }
    )
}

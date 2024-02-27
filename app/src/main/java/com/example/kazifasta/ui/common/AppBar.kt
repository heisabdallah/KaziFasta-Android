package com.example.kazifasta.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
fun AppBar(onClick: () -> Unit, title: String = "", color: Color = dark, backButton: Boolean = true) {
    TopAppBar(
        modifier = Modifier.fillMaxHeight(0.108f),
        colors = topAppBarColors(containerColor = color),
        title = {
            Row(
                modifier = Modifier.padding(top = Dimens.bigPadding),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (backButton) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "Arrow Left",
                        tint = Color.White,
                        modifier = Modifier
                            .clickable { onClick.invoke() }
                            .padding(end = Dimens.bigPadding)
                    )
                }
                Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = mateWhite)
            }
        }
    )
}

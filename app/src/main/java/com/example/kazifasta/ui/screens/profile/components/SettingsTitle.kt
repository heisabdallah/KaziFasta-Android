package com.example.kazifasta.ui.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kazifasta.ui.theme.dark
import com.example.kazifasta.ui.utils.Dimens

@Composable
fun SettingsTitle(title: String, description: String, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .background(dark)
        .padding(horizontal = Dimens.bigPadding).clickable { onClick.invoke() },
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text(text = description, fontSize = 12.sp, fontWeight = FontWeight.Light, lineHeight = 20.sp)
    }
}
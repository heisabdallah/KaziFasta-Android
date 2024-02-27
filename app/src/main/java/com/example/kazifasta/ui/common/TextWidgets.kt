package com.example.kazifasta.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.kazifasta.ui.theme.primaryTextColor
import com.example.kazifasta.ui.theme.secondaryTextColor
import com.example.kazifasta.ui.theme.tertiaryTextColor
import com.example.kazifasta.ui.utils.Dimens.bodyNormal
import com.example.kazifasta.ui.utils.Dimens.titleNormal

@Composable
fun Title(text: String, titleSize: TextUnit = titleNormal, color: Color = primaryTextColor) {
    Text(
        text = text, fontSize = titleSize,
        fontWeight = FontWeight.Bold,
        color = color
    )
}

@Composable
fun SubTitle(text: String, titleSize: TextUnit = titleNormal, color: Color = primaryTextColor) {
    Text(
        text = text, fontSize = titleSize,
        fontWeight = FontWeight.Normal,
        color = color
    )
}

@Composable
fun Description(text: String, textSize: TextUnit = bodyNormal, color: Color = tertiaryTextColor) {
    Text(
        text = text, fontSize = textSize,
        fontWeight = FontWeight.Normal,
        color = color
    )
}

@Composable
fun SectionTitle(title: String) {
    Row {
        Title(text = title, color = secondaryTextColor)
        Spacer(modifier = Modifier.fillMaxWidth())
    }
}
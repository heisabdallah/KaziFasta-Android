package com.example.kazifasta.ui.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kazifasta.ui.theme.dim
import com.example.kazifasta.ui.theme.mateBlack

@Composable
fun FullScreenImage(image: Int, name: String, onClick: () -> Unit) {
    Box(modifier = Modifier
        .clickable(indication = null,
            interactionSource = remember {
                MutableInteractionSource()
            }) { onClick.invoke() }
        .fillMaxSize()
        .background(dim), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(
                    mateBlack
                )
                .width(500.dp)
                .height(500.dp)
        ) {
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(14.dp)
            )
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}


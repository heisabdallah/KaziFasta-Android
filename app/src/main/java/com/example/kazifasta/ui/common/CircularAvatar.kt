package com.example.kazifasta.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//  Circular Avatar
@Composable
fun CircularAvatar(size: Int, image: Int, description: String, onClick: () -> Unit) {
    Image(modifier = Modifier
        .width(size.dp)
        .height(size.dp)
        .clip(shape = CircleShape)
        .clickable { onClick.invoke() },
        contentScale = ContentScale.Crop,
        painter = painterResource(id = image),
        contentDescription = description
    )
}
package com.example.kazifasta.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//  Category Tile
@Composable
fun CategoryTile(image: Int, title: String, titleColor: Color? = Color.White, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        CircularAvatar(
            size = 50,
            image = image,
            description = title,
            onClick = { onClick.invoke() })
        Text(title, fontSize = 10.sp, color = titleColor!!)
    }
}

//  Category Tile 2
@Composable
fun CategoryTile_2(
    image: Int,
    title: String,
    titleColor: Color? = Color.White,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { onClick.invoke() },
            painter = painterResource(id = image),
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
        Text(title, fontSize = 10.sp, color = titleColor!!)
    }
}

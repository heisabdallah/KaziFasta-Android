package com.example.kazifasta.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kazifasta.R
import com.example.kazifasta.ui.screens.search.SearchViewModel
import com.example.kazifasta.ui.theme.gray
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.utils.Dimens

val searchVM = SearchViewModel()

@Composable
fun SearchBar(onClick: () -> Unit = {}, enableInput: Boolean = true) {

    BasicTextField(modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .clip(shape = RoundedCornerShape(12.dp)),
        value = searchVM.searchController,
        onValueChange = { searchVM.searchController = it },
        singleLine = true,
        cursorBrush = SolidColor(gray),
        textStyle = TextStyle(color = mateBlack, textAlign = TextAlign.Start, fontSize = 16.sp),

        decorationBox = { innerTextField ->
            Row(modifier = Modifier
                .then(if (!enableInput) Modifier.clickable { onClick.invoke() } else Modifier)
                .background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 60.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search Icon", tint = gray, modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 20.dp)
                        )
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (searchVM.searchController.isEmpty()) {
                                // Show the text only when searchController is not empty
                                Text(
                                    text = "Search..",
                                    color = gray,
                                    fontSize = 14.sp,
                                )
                            }
                            if (enableInput) {
                                innerTextField()
                            }
                        }

                    }
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_circle),
                        contentDescription = "Right Arrow",
                        tint = Color.White,
                        modifier = Modifier
                            .height(30.dp)
                            .padding(horizontal = Dimens.normalPadding)
                            .clip(shape = CircleShape)
                            .background(color = green)
                            .padding(all = 4.dp)
                    )
                }

            }
        }
    )
}

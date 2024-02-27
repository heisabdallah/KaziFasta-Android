package com.example.kazifasta.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kazifasta.R
import com.example.kazifasta.ui.screens.auth.AuthViewModel
import com.example.kazifasta.ui.theme.gray
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.utils.Dimens

@Composable
fun CustomTextField2(authVM: AuthViewModel, keyboardType: String) {


    BasicTextField(modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .clip(shape = RoundedCornerShape(12.dp)),
        value = if (keyboardType == "fName") authVM.fNameText else authVM.lNameText,
        onValueChange = {
            if (keyboardType == "fName") authVM.fNameText = it else authVM.lNameText = it
        },
        singleLine = true,
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(color = mateBlack, textAlign = TextAlign.Start, fontSize = 16.sp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Rounded.Person, contentDescription = "email", tint = green)
                        Spacer(modifier = Modifier.width(Dimens.smallPadding))
                        Box(contentAlignment = Alignment.CenterStart) {
                            when (keyboardType) {
                                "fName" -> {
                                    if (authVM.fNameText.isEmpty()) {
                                        Text(
                                            text = stringResource(id = R.string.textField_first_name),
                                            color = gray, fontSize = 14.sp
                                        )
                                    }
                                }

                                "lName" -> {
                                    if (authVM.lNameText.isEmpty()) {
                                        Text(
                                            text = stringResource(id = R.string.textField_last_name),
                                            color = gray, fontSize = 14.sp
                                        )
                                    }
                                }

                                // Show the text only when searchController is not empty

                            }
//                                if (enableInput){
                            innerTextField()
//                                }
                        }

                    }
                }
            }
        }
    )
}

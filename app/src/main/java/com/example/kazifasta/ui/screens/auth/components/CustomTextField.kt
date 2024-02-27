package com.example.kazifasta.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun CustomTextField(authVM: AuthViewModel, keyboardType: String) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    BasicTextField(modifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .clip(shape = RoundedCornerShape(12.dp)),
        value = when (keyboardType) {
            "email" -> {
                authVM.emailText
            }

            "password" -> {
                authVM.passwordText
            }

            "confPassword" -> {
                authVM.confirmPasswordText
            }

            else -> {
                ""
            }
        }, onValueChange = {
            when (keyboardType) {
                "email" -> {
                    authVM.emailText = it
                }

                "password" -> {
                    authVM.passwordText = it
                }

                "confPassword" -> {
                    authVM.confirmPasswordText = it
                }

                else -> {}
            }
        }, singleLine = true,
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(color = mateBlack, textAlign = TextAlign.Start, fontSize = 16.sp),
        visualTransformation = if (keyboardType == "password" && !showPassword || keyboardType == "confPassword" && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = when (keyboardType) {
            "email" -> {
                KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false,
                    capitalization = KeyboardCapitalization.None
                )
            }

            "password" -> {
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    autoCorrect = false,
                    capitalization = KeyboardCapitalization.None
                )
            }

            "confPassword" -> {
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    autoCorrect = false,
                    capitalization = KeyboardCapitalization.None
                )
            }

            else -> {
                KeyboardOptions(keyboardType = KeyboardType.Text)
            }
        },
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
                        when (keyboardType) {
                            "email" -> {
                                Icon(
                                    Icons.Rounded.Email,
                                    contentDescription = "email",
                                    tint = green
                                )
                            }

                            "password" -> {
                                Icon(
                                    Icons.Rounded.Lock,
                                    contentDescription = "password",
                                    tint = green
                                )
                            }

                            "confPassword" -> {
                                Icon(
                                    Icons.Rounded.Lock,
                                    contentDescription = "Confirm password",
                                    tint = green
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(Dimens.smallPadding))
                        Box(contentAlignment = Alignment.CenterStart) {
                            when (keyboardType) {
                                "email" -> {
                                    if (authVM.emailText.isEmpty()) {
                                        Text(
                                            text = stringResource(id = R.string.textField_email),
                                            color = gray, fontSize = 14.sp
                                        )
                                    }
                                }

                                "password" -> {
                                    if (authVM.passwordText.isEmpty()) {
                                        Text(
                                            text = stringResource(id = R.string.textField_password),
                                            color = gray, fontSize = 14.sp
                                        )
                                    }
                                }

                                "confPassword" -> {
                                    if (authVM.confirmPasswordText.isEmpty()) {
                                        Text(
                                            text = stringResource(id = R.string.textField_confirm_password),
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
                    if (keyboardType != "email") {
                        Icon(Icons.Rounded.Face,
                            contentDescription = null,
                            tint = green,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickable { showPassword = !showPassword })
                    }
                }
            }
        }
    )
}

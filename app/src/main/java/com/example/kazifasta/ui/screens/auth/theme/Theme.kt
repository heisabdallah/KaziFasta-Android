package com.example.kazifasta.ui.screens.auth.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.kazifasta.ui.theme.Typography
import com.example.kazifasta.ui.theme.green
import com.example.kazifasta.ui.theme.mateBlack
import com.example.kazifasta.ui.theme.mateWhite

private val lightColorScheme = lightColorScheme(
    primary = green,
    secondary = mateBlack,
    tertiary = mateWhite,

    /* Other default colors to override */

    background = mateBlack,
    surface = mateBlack,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = mateBlack,
    onBackground = mateBlack,
    onSurface = mateBlack,
)


@Composable
fun AuthActivityTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = lightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {

        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, window.decorView)
                .apply { isAppearanceLightStatusBars = false }
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = green.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
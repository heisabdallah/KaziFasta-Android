package com.example.kazifasta.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

object NavigationTransitions {

    private const val delayMillis: Int = 20
    private const val durationMillis: Int = 200

    fun scaleIntoContainer(
    ): EnterTransition {
        return scaleIn(
            animationSpec = tween(durationMillis = durationMillis, delayMillis = delayMillis),
            initialScale = 1f
        ) + fadeIn(animationSpec = tween(durationMillis = durationMillis, delayMillis = delayMillis))
    }

    fun scaleOutOfContainer(
    ): ExitTransition {
        return scaleOut(
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = delayMillis
            ), targetScale = 1.1f
        ) + fadeOut(tween(delayMillis = delayMillis))
    }

    fun slideInVertically(
    ): EnterTransition {
        return slideInVertically ( initialOffsetY = { fullHeight -> fullHeight / 3},
            animationSpec = tween(
                durationMillis = 200, // Adjust duration as needed
                easing = LinearOutSlowInEasing // Adjust easing as needed
            ) )
    }

    fun slideOutVertically(
    ): ExitTransition {
        return slideOutVertically (targetOffsetY = { fullHeight -> fullHeight })
    }
}


package com.jefisu.contacts.core.presentation.components

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(
    isVisible: Boolean = true,
    enterAnimation: EnterTransition = fadeIn() + expandVertically(),
    exitTransition: ExitTransition = fadeOut() + shrinkVertically(),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = enterAnimation,
        exit = exitTransition,
        initiallyVisible = false
    ) {
        content()
    }
}
package com.jefisu.contacts.core.presentation.components

import androidx.compose.runtime.Composable

@Composable
fun DividerAtEndToList(
    letter: String,
    items: List<Int>,
    content: @Composable () -> Unit
) {
    items.forEach { n ->
        val isNumber = letter == n.toString()
        if (isNumber && n == 9) {
            content()
        }
    }
    if (letter !in items.toString()) {
        content()
    }
}
package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jefisu.contacts.core.presentation.ui.theme.spacing

@Composable
fun DividerAtEndToList(
    letter: String,
    items: List<Int> = (1..9).toList(),
    content: @Composable () -> Unit = {
        Divider(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    }
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
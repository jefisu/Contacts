package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StandardButton(
    text: String,
    textSize: TextUnit = 24.sp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    selected: Boolean = true,
    selectedColor: Color = LocalContentColor.current,
    unSelectedColor: Color = LocalContentColor.current.copy(ContentAlpha.disabled),
    onClickAction: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .background(backgroundColor)
            .clip(CircleShape)
            .clickable { onClickAction() }
    ) {
        Text(
            text = text,
            fontSize = textSize,
            color = if (selected) selectedColor else unSelectedColor,
            modifier = Modifier.padding(
                horizontal = 32.dp,
                vertical = 8.dp
            )
        )
    }
}
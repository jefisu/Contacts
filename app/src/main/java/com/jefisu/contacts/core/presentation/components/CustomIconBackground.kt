package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconBackground(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color = Color.White,
    size: Dp,
    backgroundShape: Shape = CircleShape,
    space: Dp = 8.dp,
    backgroundColor: Color = Color.DarkGray.copy(0.4f),
    description: String?,
) {
    Box(
        modifier = modifier
            .clip(backgroundShape)
            .background(backgroundColor)
            .padding(space)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = tint,
            modifier = Modifier.size(size)
        )
    }
}
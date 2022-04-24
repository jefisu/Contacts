package com.jefisu.contacts.features_contacts.presentation.contact_info.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.core.presentation.components.IconAnimation

@Composable
fun RowScope.BottomIconButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    text: String,
    icon: ImageVector,
    description: String?,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .weight(0.5f)
            .clickable { onClick() }
    ) {
        if (icon == Icons.Default.Edit) {
            Icon(imageVector = icon, contentDescription = description)
        } else {
            IconAnimation(
                selected = isFavorite,
                icon = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                color = if (isFavorite) Color.Yellow else MaterialTheme.colors.onSurface
            )
        }
        Text(text = text)
    }
}
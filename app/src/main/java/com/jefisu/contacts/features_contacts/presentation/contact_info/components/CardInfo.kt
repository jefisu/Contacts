package com.jefisu.contacts.features_contacts.presentation.contact_info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.contacts.core.presentation.components.IconShapeBackground
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.phoneVisualTransformation

@Composable
fun CardInfo(
    modifier: Modifier = Modifier,
    phone: String = "",
    email: String = "",
    icon: ImageVector,
    iconColor: Color = MaterialTheme.colors.onSurface,
    iconBackgroundColor: Color,
    backgroundColor: Color = Color.DarkGray.copy(0.5f),
    backgroundShape: Shape = RoundedCornerShape(16.dp),
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onSurface,
        fontSize = 20.sp
    )
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(backgroundShape)
            .background(backgroundColor)
            .padding(12.dp)
    ) {
        IconShapeBackground(
            icon = icon,
            tint = iconColor,
            size = 25.dp,
            backgroundColor = iconBackgroundColor,
            description = null
        )
        BasicTextField(
            value = phone.ifBlank { email },
            onValueChange = { /* Is not change */ },
            visualTransformation = {
                if (phone.isNotBlank()) {
                    phoneVisualTransformation(it)
                } else {
                    TransformedText(it, OffsetMapping.Identity)
                }
            },
            readOnly = true,
            textStyle = textStyle
        )
    }
}
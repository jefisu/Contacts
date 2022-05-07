package com.jefisu.contacts.features_contacts.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.features_contacts.domain.model.Contact

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ContactItem(
    contact: Contact,
    initialLetter: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    onLongClickSelectDelete: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClickSelectDelete
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) MaterialTheme.colors.onSurface.copy(0.7f) else MaterialTheme.colors.onSurface)
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Is selected",
                        tint = MaterialTheme.colors.surface
                    )
                } else {
                    val isNumber = initialLetter in (0..9).toList().toString()
                    Text(
                        text = if (isNumber) "#" else initialLetter.uppercase(),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.surface
                    )
                }
            }
            Text(
                text = contact.name,
                style = MaterialTheme.typography.h6
            )
        }
        if (contact.isFavorite) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = Color.Yellow,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = MaterialTheme.spacing.small)
            )
        }
    }
}
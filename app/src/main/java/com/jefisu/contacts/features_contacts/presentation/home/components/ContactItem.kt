package com.jefisu.contacts.features_contacts.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.core.presentation.components.IconButtonAnimation
import com.jefisu.contacts.features_contacts.domain.model.Contact

@ExperimentalMaterialApi
@Composable
fun ContactItem(
    contact: Contact,
    initialLetter: String,
    modifier: Modifier = Modifier,
    onNavigateClick: (Int?) -> Unit = {},
    onSwipedDelete: (Contact) -> Unit = {},
    onFavoriteClick: (selected: Boolean) -> Unit = {}
) {
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
        onSwipedDelete(contact)
    }
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd),
        background = {}
    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable { onNavigateClick(contact.id) },
            contentAlignment = Alignment.CenterEnd
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
                        .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
                ) {
                    Text(
                        text = when {
                            initialLetter in (0..9).toList().toString() -> "#"
                            else -> initialLetter.uppercase()
                        },
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.h6
                )
            }
            IconButtonAnimation(
                selected = contact.isFavorite,
                icon = if (contact.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                color = if (contact.isFavorite) Color.Red else MaterialTheme.colors.onSurface,
                onClick = {
                    onFavoriteClick(!contact.isFavorite)
                }
            )
        }
    }
}
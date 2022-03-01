package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.features_contacts.domain.model.Contact


@ExperimentalMaterialApi
@Composable
fun Item(
    contact: Contact,
    modifier: Modifier = Modifier,
    activeSwipedDelete: Boolean = true,
    onSwipedDelete: () -> Unit = {},
) {
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
        onSwipedDelete()
    }
    SwipeToDismiss(
        state = dismissState,
        directions = if (activeSwipedDelete) setOf(DismissDirection.StartToEnd) else setOf(),
        background = {}
    ) {
        ListItem(
            modifier = modifier,
            text = {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.h5
                )
            },
            icon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = Color.DarkGray, shape = CircleShape)
                        .padding(LocalSpacing.current.small)
                        .defaultMinSize(24.dp)
                ) {
                    Text(
                        text = contact.name.take(1).capitalize(),
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
            },
            trailing = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Send email"
                )
            }
        )
    }
}
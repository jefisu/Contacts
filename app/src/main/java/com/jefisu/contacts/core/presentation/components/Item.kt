package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.features_contacts.domain.model.Contact

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Item(
    contact: Contact,
    modifier: Modifier = Modifier,
    activeSwipedDelete: Boolean = true,
    onSwipedDelete: () -> Unit = {},
) {
    val space = LocalSpacing.current
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
        onSwipedDelete()
    }
    SwipeToDismiss(
        state = dismissState,
        directions = if (activeSwipedDelete) setOf(DismissDirection.StartToEnd) else setOf(),
        background = {}
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = Color.DarkGray, shape = CircleShape)
                    .padding(space.small)
                    .defaultMinSize(24.dp)
            ) {
                Text(
                    text = contact.name.take(1).capitalize(),
                    fontSize = 28.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(space.medium))
            Text(text = contact.name, style = MaterialTheme.typography.h4)
        }
    }
}
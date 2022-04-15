package com.jefisu.contacts.features_contacts.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.contacts.core.presentation.components.IconButtonAnimation
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.features_contacts.domain.model.Contact


@ExperimentalMaterialApi
@Composable
fun ContactItem(
    contact: Contact,
    modifier: Modifier = Modifier,
    onSwipedDelete: () -> Unit = {},
    onFavoriteClick: () -> Unit = {}
) {
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
        onSwipedDelete()
    }
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd),
        background = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Red)
            )
        }
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = Color.DarkGray, shape = CircleShape)
                        .padding(MaterialTheme.spacing.small)
                        .defaultMinSize(24.dp)
                ) {
                    Text(
                        text = contact.name.take(1).uppercase(),
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.h5
                )
            }
            IconButtonAnimation(
                selected = contact.isFavorite,
                icon = Icons.Default.FavoriteBorder,
                onClick = onFavoriteClick
            )
        }
    }
}
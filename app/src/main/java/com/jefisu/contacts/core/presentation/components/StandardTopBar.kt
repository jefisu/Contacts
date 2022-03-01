package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun StandardTopBar(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit = {},
    onNavigateSearch: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.h4)
        Row {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Add/sortBy contact"
                )
            }
            IconButton(onClick = onNavigateSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search contact"
                )
            }
        }
    }
}
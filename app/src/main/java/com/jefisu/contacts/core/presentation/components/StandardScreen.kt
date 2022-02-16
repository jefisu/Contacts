package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.features_contacts.domain.model.Contact

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StandardScreen(
    title: String,
    icon: ImageVector = Icons.Default.Add,
    onClick: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    onNavigateSearch: () -> Unit = {},
    onSwipedDelete: (Contact) -> Unit = {},
    contacts: List<Contact>,
    orderSectionContent: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small)
    ) {
        StandardTopBar(
            title = title,
            icon = icon,
            onClick = onClick,
            onNavigateSearch = onNavigateSearch
        )
        orderSectionContent()
        LazyColumn {
            items(contacts) { contact ->
                Item(
                    contact = contact,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.small)
                        .clickable {
                            onNavigate(contact.id.toString())
                        },
                    activeSwipedDelete = true,
                    onSwipedDelete = {
                        onSwipedDelete(contact)
                    }
                )
            }
        }
    }
}
package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.features_contacts.domain.model.Contact

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun StandardScreen(
    title: String,
    icon: ImageVector = Icons.Default.Add,
    onClick: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    onNavigateSearch: () -> Unit = {},
    onSwipedDelete: (Contact) -> Unit = {},
    groupedContacts: Map<String, List<Contact>>,
    orderSectionContent: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandardTopBar(
            title = title,
            icon = icon,
            onClick = onClick,
            onNavigateSearch = onNavigateSearch
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.small))
        orderSectionContent()
        GroupedList(
            grouped = groupedContacts,
            item = {
                Item(
                    contact = it,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onNavigate(it.id.toString())
                        },
                    activeSwipedDelete = true,
                    onSwipedDelete = {
                        onSwipedDelete(it)
                    }
                )
            }
        )
    }
}
package com.jefisu.contacts.features_contacts.presentation.contact_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.core.presentation.components.CustomIconBackground
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.contact_info.components.BasicCard
import com.jefisu.contacts.features_contacts.presentation.contact_info.components.BottomIconButton
import com.jefisu.contacts.features_contacts.presentation.contact_info.components.CardInfo

@Composable
fun ContactInfoScreen(
    navController: NavController,
    viewModel: ContactInfoViewModel = hiltViewModel()
) {
    val contact = viewModel.contact
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = navController::navigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "Back to home screen"
                )
            }
        }
        Spacer(modifier = Modifier.height(120.dp))
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                BasicCard(name = contact.name)
                CardInfo(
                    phone = contact.phone,
                    icon = Icons.Default.Phone,
                    iconBackgroundColor = Color.Green
                )
                if (contact.email.isNotBlank()) {
                    CardInfo(
                        email = contact.email,
                        icon = Icons.Default.Email,
                        iconBackgroundColor = MaterialTheme.colors.surface
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    BottomIconButton(
                        text = "Edit",
                        icon = Icons.Default.Edit,
                        description = "Edit contact information",
                        onClick = {
                            navController.navigate(Screen.AddEdit.navArg(contact.id))
                        },
                        isFavorite = false
                    )
                    BottomIconButton(
                        text = "Favorite",
                        icon = Icons.Default.Star,
                        description = "Add contact to favorites",
                        isFavorite = contact.isFavorite,
                        onClick = {
                            viewModel.onEvent(ContactInfoEvent.AddRemoveFavorite)
                        }
                    )
                }
            }
            CustomIconBackground(
                icon = Icons.Default.Person,
                size = 100.dp,
                description = "Icon contact",
                modifier = Modifier.graphicsLayer {
                    translationY = -68.dp.toPx()
                }
            )
        }
    }
}
package com.jefisu.contacts.features_contacts.presentation.recents

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.core.presentation.components.StandardScreen
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.recents.components.OrderSection

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecentsScreen(
    navController: NavController,
    viewModel: RecentsViewModel = hiltViewModel()
) {
    StandardScreen(
        title = "Recents",
        icon = Icons.Default.Sort,
        contacts = viewModel.state.contacts,
        onClick = {
            viewModel.onEvent(RecentsEvent.ToggleOrderSection)
        },
        onNavigate = { id ->
            navController.navigate(Screen.AddEdit.route + "?id=${id}")
        },
        onNavigateSearch = {
            navController.navigate(Screen.Search.route)
        },
        orderSectionContent = {
            AnimatedVisibility(
                visible = viewModel.state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    contactOrder = viewModel.state.contactOrder,
                    onOrderChange = {
                        viewModel.onEvent(RecentsEvent.Order(it))
                    }
                )
            }
        }
    )
}
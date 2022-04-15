package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.components.EnterAnimation
import com.jefisu.contacts.core.presentation.components.StandardTopBar
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.home.components.ContactItem
import com.jefisu.contacts.features_contacts.presentation.home.components.OrderSection
import com.jefisu.contacts.features_contacts.presentation.home.components.SearchTextField

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isResearchedEnabled by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        when (isResearchedEnabled) {
            false -> StandardTopBar(
                title = stringResource(R.string.app_name),
                icon = Icons.Default.Add,
                onClick = { navController.navigate(Screen.AddEdit.route) },
                onNavigateSearch = { isResearchedEnabled = true },
                onSortClick = { viewModel.onEvent(HomeEvent.ToggleOrderSection) }
            )
            true -> EnterAnimation {
                SearchTextField(
                    text = viewModel.state.query,
                    onTextChange = { viewModel.onEvent(HomeEvent.OnFilterContacts(it)) },
                    hint = "Search",
                    onClickClearText = { viewModel.onEvent(HomeEvent.ClearResearchText()) },
                    onClickBack = { isResearchedEnabled = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.small)
                )
            }
        }
        if (!viewModel.state.isOrderSectionVisible) {
            Spacer(modifier = Modifier.height(8.dp))
        }
        EnterAnimation(
            isVisible = viewModel.state.isOrderSectionVisible,
            content = {
                OrderSection(
                    contactOrder = viewModel.state.contactOrder,
                    onOrderChange = { viewModel.onEvent(HomeEvent.Order(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.medium)
                )
            }
        )
        if (viewModel.state.isOrderSectionVisible) {
            Spacer(modifier = Modifier.height(12.dp))
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.state.contacts) {
                ContactItem(
                    contact = it,
                    onSwipedDelete = { viewModel.onEvent(HomeEvent.DeleteContact(it)) },
                    onFavoriteClick = {
                        viewModel.onEvent(
                            HomeEvent.AddRemoveFavorite(
                                selected = !it.isFavorite,
                                contact = it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navController.navigate(Screen.AddEdit.navArg(it.id))
                        }
                )
            }
        }
    }
}
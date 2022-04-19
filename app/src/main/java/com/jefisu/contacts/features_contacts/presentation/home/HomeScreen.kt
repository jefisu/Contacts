package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.ui.theme.BlueAlternative
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.domain.util.OrderType
import com.jefisu.contacts.features_contacts.presentation.home.components.ContactItem
import com.jefisu.contacts.features_contacts.presentation.home.components.SearchTextField

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.small
            )
    ) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h5)
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchTextField(
                text = state.query,
                onTextChange = { viewModel.onEvent(HomeEvent.OnFilterContacts(it)) },
                hint = "Search",
                onClickClearText = { viewModel.onEvent(HomeEvent.ClearResearchText()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
            )
            IconButton(
                onClick = {
                    if (state.contactOrder.orderType != OrderType.Ascending) {
                        viewModel.onEvent(HomeEvent.Order(state.contactOrder.copy(OrderType.Ascending)))
                    } else {
                        viewModel.onEvent(HomeEvent.Order(state.contactOrder.copy(OrderType.Descending)))
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BlueAlternative)
            ) {
                Icon(
                    imageVector = Icons.Default.SortByAlpha,
                    contentDescription = "Sort contacts",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            state.contacts.forEach { (letter, contacts) ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colors.onSurface.copy(0.05f))
                        .padding(MaterialTheme.spacing.small)
                ) {
                    contacts.forEach { contact ->
                        ContactItem(
                            contact = contact,
                            initialLetter = letter,
                            onSwipedDelete = { viewModel.onEvent(HomeEvent.DeleteContact(contact)) },
                            onFavoriteClick = {
                                viewModel.onEvent(
                                    HomeEvent.AddRemoveFavorite(
                                        selected = !contact.isFavorite,
                                        contact = contact
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable(
                                    onClick = { navController.navigate(Screen.AddEdit.navArg(contact.id)) }
                                )
                        )
                        if (contact != contacts.last()) {
                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                        }
                    }
                }
            }
        }
    }
}
package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.jefisu.contacts.core.presentation.components.DividerAtEndToList
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
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.extraSmall
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
        LazyColumn {
            state.contacts.forEach { (letter, contacts) ->
                if (letter == "a".uppercase()) {
                    item {
                        Divider(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                    }
                }
                items(contacts) {
                    ContactItem(
                        contact = it,
                        initialLetter = letter,
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
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                navController.navigate(Screen.AddEdit.navArg(it.id))
                            }
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                }
                item {
                    DividerAtEndToList(letter = letter)
                }
            }
        }
    }
}
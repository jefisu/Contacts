package com.jefisu.contacts.features_contacts.presentation.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.home.components.ContactItem
import com.jefisu.contacts.features_contacts.presentation.search.components.SearchTextField

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(
            text = state.query,
            onTextChange = { viewModel.onEvent(SearchEvent.OnFilterContacts(it)) },
            hint = "Search",
            onNavigateBackClick = navController::navigateUp,
            onClickClearText = { viewModel.onEvent(SearchEvent.ClearSearchText) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        ) {
            val showContactsSearched =
                state.query.isNotBlank() && state.contacts.isNotEmpty()
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colors.onSurface.copy(0.05f))
                        .padding(if (showContactsSearched) MaterialTheme.spacing.small else MaterialTheme.spacing.default)
                        .animateContentSize()
                ) {
                    if (showContactsSearched) {
                        state.contacts.forEach { contact ->
                            ContactItem(
                                contact = contact,
                                initialLetter = contact.name.take(1),
                                onClick = {
                                    navController.navigate(Screen.ContactInfo.navArg(contact.id))
                                }
                            )
                            if (contact != state.contacts.last()) {
                                Divider(
                                    modifier = Modifier
                                        .padding(top = MaterialTheme.spacing.small)
                                        .padding(horizontal = MaterialTheme.spacing.small)
                                )
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                            }
                        }
                    }
                }
            }
        }
    }
}
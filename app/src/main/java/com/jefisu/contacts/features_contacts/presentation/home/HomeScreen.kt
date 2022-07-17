package com.jefisu.contacts.features_contacts.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.ui.theme.NavyBlue
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.home.components.ContactItem
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()
    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val contactsIsSelected = viewModel.selectedContacts.isNotEmpty()
    val isExpanded = collapsingState.toolbarState.progress == 1f

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddEdit.route) },
                backgroundColor = NavyBlue
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAddAlt,
                    contentDescription = "Add contact",
                    tint = Color.White
                )
            }
        }
    ) {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = collapsingState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.padding(8.dp),
            toolbar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .pin()
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(
                            start = if (!contactsIsSelected) MaterialTheme.spacing.small else MaterialTheme.spacing.default
                        )
                        .road(
                            whenCollapsed = if (contactsIsSelected) Alignment.Center else Alignment.CenterStart,
                            whenExpanded = Alignment.Center
                        )
                ) {
                    Text(
                        text = stringResource(
                            id = if (contactsIsSelected) R.string.selected_contacts else R.string.app_name,
                            viewModel.selectedContacts.size
                        ),
                        style = if (isExpanded) MaterialTheme.typography.h4 else MaterialTheme.typography.h5
                    )
                    if (isExpanded && !contactsIsSelected) {
                        Text(text = stringResource(id = R.string.contact_in_device, state.listSize))
                    }
                }
                if (contactsIsSelected) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.road(Alignment.CenterStart, Alignment.BottomStart)
                    ) {
                        Checkbox(
                            checked = state.contacts.size == viewModel.selectedContacts.size,
                            onCheckedChange = { viewModel.onEvent(HomeEvent.OnSelectAllContacts) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colors.onSurface
                            )
                        )
                        Text(text = "All")
                    }
                }
                IconButton(
                    onClick = {
                        if (contactsIsSelected) {
                            viewModel.onEvent(HomeEvent.DeleteContact)
                            return@IconButton
                        }
                        navController.navigate(Screen.Search.route)
                    },
                    modifier = Modifier.road(
                        whenCollapsed = Alignment.CenterEnd,
                        whenExpanded = Alignment.BottomEnd
                    )
                ) {
                    Icon(
                        imageVector = if (contactsIsSelected) Icons.Default.DeleteForever else Icons.Default.Search,
                        contentDescription = "Search/Delete contact"
                    )
                }
            }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .clip(RoundedCornerShape(24.dp))
                    .verticalScroll(scrollState)
            ) {
                state.contacts
                    .groupBy { it.name.take(1) }
                    .forEach { (letter, contacts) ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp))
                                .background(MaterialTheme.colors.onSurface.copy(0.05f))
                                .padding(MaterialTheme.spacing.small)
                                .animateContentSize()
                        ) {
                            contacts.forEach { contact ->
                                val isSelectedContact =
                                    viewModel.selectedContacts.any { it.id == contact.id }
                                ContactItem(
                                    contact = contact,
                                    initialLetter = letter,
                                    isSelected = isSelectedContact,
                                    onClick = {
                                        if (contactsIsSelected) {
                                            viewModel.onEvent(HomeEvent.OnSelectContact(contact))
                                            return@ContactItem
                                        }
                                        navController.navigate(Screen.ContactInfo.navArg(contact.id))
                                    },
                                    onLongClickSelectDelete = {
                                        if (contactsIsSelected) {
                                            return@ContactItem
                                        }
                                        viewModel.onEvent(HomeEvent.OnSelectContact(contact))
                                    }
                                )
                                if (contact != contacts.last()) {
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
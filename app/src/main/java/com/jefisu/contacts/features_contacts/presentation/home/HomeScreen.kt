package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.home.components.ContactItem
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

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
    val collapsingState = rememberCollapsingToolbarScaffoldState()
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
                modifier = Modifier.road(
                    whenCollapsed = Alignment.TopStart,
                    whenExpanded = Alignment.Center
                )
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h4
                )
                if (collapsingState.toolbarState.progress == 1f) {
                    Text(text = "${state.listSize} contacts in device")
                }
            }
            IconButton(
                onClick = { navController.navigate(Screen.Search.route) },
                modifier = Modifier.road(
                    whenCollapsed = Alignment.TopEnd,
                    whenExpanded = Alignment.BottomEnd
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search contact"
                )
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
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
                            onNavigateClick = { navController.navigate(Screen.AddEdit.navArg(it)) },
                            onSwipedDelete = { viewModel.onEvent(HomeEvent.DeleteContact(it)) },
                            onFavoriteClick = { selected ->
                                viewModel.onEvent(HomeEvent.AddRemoveFavorite(selected, contact))
                            }
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
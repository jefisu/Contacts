package com.jefisu.contacts.features_contacts.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.components.StandardScreen
import com.jefisu.contacts.core.presentation.util.Screen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    StandardScreen(
        title = stringResource(R.string.app_name),
        groupedContacts = viewModel.state.groupedContacts,
        onClick = {
            navController.navigate(Screen.AddEdit.route)
        },
        onNavigate = { id ->
            navController.navigate(Screen.AddEdit.route + "?id=${id}")
        },
        onNavigateSearch = {
            navController.navigate(Screen.Search.route)
        },
        onSwipedDelete = { contact ->
            viewModel.onEvent(HomeEvent.DeleteContact(contact))
        }
    )
}
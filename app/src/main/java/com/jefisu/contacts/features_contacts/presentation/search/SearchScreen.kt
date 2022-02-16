package com.jefisu.contacts.features_contacts.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jefisu.contacts.core.presentation.components.Item
import com.jefisu.contacts.core.presentation.components.StandardTextField
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.core.presentation.util.Screen

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.medium)
    ) {
        StandardTextField(
            text = viewModel.state.query,
            onTextChange = viewModel::onFilterContacts,
            icon1 = Icons.Default.ArrowBack,
            hint = "Search",
            onClick = navController::navigateUp,
            onClickClearText = viewModel::clearText
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(viewModel.state.contacts) { contact ->
                Item(
                    contact = contact,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.AddEdit.route + "?id=${contact.id}")
                        },
                    activeSwipedDelete = false
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}
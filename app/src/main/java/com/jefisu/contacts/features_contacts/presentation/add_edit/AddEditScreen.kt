package com.jefisu.contacts.features_contacts.presentation.add_edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.components.StandardButton
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
import com.jefisu.contacts.features_contacts.presentation.add_edit.components.AddEditTextField
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val space = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditViewModel.UiEvent.AddSuccessfully -> navController.navigateUp()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = space.small,
                end = space.medium,
                top = space.large
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Black, shape = CircleShape)
                .padding(space.extraSmall),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = "Icon contact",
                modifier = Modifier.size(70.dp)
            )
        }
        Spacer(modifier = Modifier.height(space.large))
        AddEditTextField(
            text = viewModel.state.name,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredName(it)) },
            hint = "Name",
            icon = Icons.Default.Person,
            onClickClearText = {
                viewModel.onEvent(AddEditEvent.ClearTextName)
            }
        )
        Spacer(modifier = Modifier.height(space.medium))
        AddEditTextField(
            text = viewModel.state.phone,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredPhone(it)) },
            hint = "Phone",
            icon = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            onClickClearText = {
                viewModel.onEvent(AddEditEvent.ClearTextPhone)
            }
        )
        Spacer(modifier = Modifier.height(space.medium))
        AddEditTextField(
            text = viewModel.state.email,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredEmail(it)) },
            hint = "Email",
            icon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onClickClearText = {
                viewModel.onEvent(AddEditEvent.ClearTextEmail)
            }
        )
        Row(
            modifier = Modifier.weight(0.1f),
            verticalAlignment = Alignment.Bottom
        ) {
            StandardButton(
                text = "Cancel",
                onClickAction = navController::navigateUp
            )
            StandardButton(
                text = "Save",
                onClickAction = { viewModel.onEvent(AddEditEvent.AddContact) }
            )
        }
    }
}
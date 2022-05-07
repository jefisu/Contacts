package com.jefisu.contacts.features_contacts.presentation.add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.contacts.core.presentation.components.IconShapeBackground
import com.jefisu.contacts.core.presentation.ui.theme.spacing
import com.jefisu.contacts.features_contacts.presentation.add_edit.components.AddEditTextField
import com.jefisu.contacts.features_contacts.presentation.add_edit.components.BottomButton
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.phoneVisualTransformation

@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LaunchedEffect(key1 = true) {
        viewModel.eventChannel.collect { event ->
            if (event == AddEditViewModel.UiEvent.AddSuccessfully) {
                navController.navigateUp()
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.large,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {
        IconShapeBackground(
            icon = Icons.Default.Person,
            size = 100.dp,
            description = "Icon contact"
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        AddEditTextField(
            text = state.name,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredName(it)) },
            hint = "Name",
            icon = Icons.Default.Person,
            onClickClearText = { viewModel.onEvent(AddEditEvent.ClearTextName) },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            errorMessage = state.nameErrorMessage?.asString()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        AddEditTextField(
            text = state.phone,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredPhone(it)) },
            hint = "Phone",
            icon = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onClickClearText = { viewModel.onEvent(AddEditEvent.ClearTextPhone) },
            visualTransformation = { phoneVisualTransformation(it) },
            errorMessage = state.phoneErrorMessage?.asString()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        AddEditTextField(
            text = state.email,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredEmail(it)) },
            hint = "Email",
            icon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onClickClearText = { viewModel.onEvent(AddEditEvent.ClearTextEmail) },
            errorMessage = state.emailErrorMessage?.asString()
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            BottomButton(
                text = "Cancel",
                onClick = navController::navigateUp
            )
            BottomButton(
                text = "Save",
                onClick = { viewModel.onEvent(AddEditEvent.AddContact) }
            )
        }
    }
}
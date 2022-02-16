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
import com.jefisu.contacts.core.presentation.components.StandardTextField
import com.jefisu.contacts.core.presentation.ui.theme.LocalSpacing
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
            .padding(space.medium),
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
        Spacer(modifier = Modifier.height(space.medium))
        StandardTextField(
            text = viewModel.state.name,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredName(it)) },
            hint = "Name",
            icon1 = Icons.Default.Person,
            onClickClearText = {
                viewModel.onEvent(AddEditEvent.ClearTextName)
            }
        )
        Spacer(modifier = Modifier.height(space.medium))
        StandardTextField(
            text = viewModel.state.phone,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredPhone(it)) },
            hint = "Phone",
            icon1 = Icons.Default.Phone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            onClickClearText = {
                viewModel.onEvent(AddEditEvent.ClearTextPhone)
            }
        )
        Spacer(modifier = Modifier.height(space.medium))
        StandardTextField(
            text = viewModel.state.email,
            onTextChange = { viewModel.onEvent(AddEditEvent.EnteredEmail(it)) },
            hint = "Email",
            icon1 = Icons.Default.Email,
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
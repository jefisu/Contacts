package com.jefisu.contacts.features_contacts.presentation.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.contacts.core.presentation.util.Constants.MAX_EMAIL_CHAR
import com.jefisu.contacts.core.presentation.util.Constants.MAX_NAME_CHAR
import com.jefisu.contacts.core.presentation.util.Constants.MAX_NUMBER_CHAR
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.validateEmail
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.validateName
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.validatePhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AddEditState())
        private set

    private var _currentContact: Contact? = null

    private val _eventChannel = Channel<UiEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            repository.getContact(id.toInt())
                .onEach { contact ->
                    _currentContact = contact
                    _currentContact?.let {
                        state = state.copy(
                            name = it.name,
                            phone = it.phone,
                            email = it.email
                        )
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.AddContact -> {
                saveContact()
            }
            is AddEditEvent.EnteredName -> {
                if (event.name.length <= MAX_NAME_CHAR) {
                    state = state.copy(name = event.name)
                }
            }
            is AddEditEvent.EnteredPhone -> {
                if (event.phone.length <= MAX_NUMBER_CHAR) {
                    state = state.copy(phone = event.phone)
                }
            }
            is AddEditEvent.EnteredEmail -> {
                if (event.email.length <= MAX_EMAIL_CHAR) {
                    state = state.copy(email = event.email)
                }
            }
            is AddEditEvent.ClearTextName -> state = state.copy(name = "")
            is AddEditEvent.ClearTextPhone -> state = state.copy(phone = "")
            is AddEditEvent.ClearTextEmail -> state = state.copy(email = "")
        }
    }

    private fun saveContact() {
        val nameResult = validateName(state.name)
        val phoneResult = validatePhone(state.phone)
        val emailResult = validateEmail(state.email)

        val hasError = listOf(
            nameResult,
            phoneResult,
            emailResult
        ).any { it.errorMessage != null }

        if (hasError) {
            state = state.copy(
                nameErrorMessage = nameResult.errorMessage,
                phoneErrorMessage = phoneResult.errorMessage,
                emailErrorMessage = emailResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            repository.insertContact(
                Contact(
                    name = state.name,
                    phone = state.phone,
                    email = state.email,
                    isFavorite = _currentContact?.isFavorite ?: false,
                    id = _currentContact?.id
                )
            )
            _eventChannel.send(UiEvent.AddSuccessfully)
        }
    }

    sealed class UiEvent {
        object AddSuccessfully : UiEvent()
    }
}
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
import com.jefisu.contacts.core.presentation.util.Resource
import com.jefisu.contacts.core.presentation.util.UiText
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _currentContact = repository.getContact(id.toInt())
                _currentContact?.let {
                    state = state.copy(
                        name = it.name,
                        phone = it.phone,
                        email = it.email
                    )
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.AddContact -> {
                viewModelScope.launch {
                    val result = repository.insertContact(
                        Contact(state.name, state.phone, state.email, id = _currentContact?.id)
                    )
                    when (result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.AddSuccessfully)
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(result.uiText ?: UiText.unknownError())
                            )
                        }
                    }
                }
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

    sealed class UiEvent {
        data class ShowSnackBar(val uiText: UiText) : UiEvent()
        object AddSuccessfully : UiEvent()
    }
}